package com.microservicio.rimextraccion.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.Tuple;
import com.commons.utils.constants.Messages;
import com.commons.utils.controllers.CommonController;
import com.commons.utils.errors.CreateTableWarning;
import com.commons.utils.errors.DataAccessEmptyWarning;
import com.commons.utils.utils.Response;
import com.microservicio.rimextraccion.dto.TablaDinamicaDto;
import com.microservicio.rimextraccion.helpers.DataModelHelper;
import com.microservicio.rimextraccion.models.constants.AlterTableType;
import com.microservicio.rimextraccion.models.entities.TablaDinamica;
import com.microservicio.rimextraccion.services.RimextraccionService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

@CrossOrigin(origins = { "*" })
@RestController
public class RimextraccionController extends CommonController<TablaDinamica, RimextraccionService> {

   @PostMapping(path = { "/save" })
   public ResponseEntity<?> save(@RequestBody TablaDinamicaDto tablaDinamicaDto) {

      String nombreTabla = tablaDinamicaDto.getNombre();

      /* » Validar: Si, existe tabla... */
      Optional<TablaDinamica> tablaDinamicaOld = super.service.findByNombre(nombreTabla);
      if (!tablaDinamicaOld.isEmpty())
         throw new CreateTableWarning(Messages.WARNING_CREATE_TABLE(nombreTabla));

      /* » Insertar: Nombre de tabla dinámica ... */
      TablaDinamica tablaDinamicaNew = new ModelMapper().map(tablaDinamicaDto, TablaDinamica.class);
      this.service.save(tablaDinamicaNew);

      /* » ... */
      StringBuilder cuerpoTabla = new StringBuilder();
      cuerpoTabla.append(tablaDinamicaDto.getCamposCsv()).append(")");

      /* » Insertar: Tabla dinámica ... */
      this.service.createTable(nombreTabla, cuerpoTabla.toString());

      return ResponseEntity.ok().body(
            Response
                  .builder()
                  .message(Messages.SUCCESS_CREATE_TABLE(nombreTabla))
                  .data(this.service.findAll())
                  .build());
   }

   @PostMapping(path = { "/uploadExtraccion" })
   public ResponseEntity<?> uploadExtraccion(@RequestParam String nombreTabla,
         @RequestPart(required = false) MultipartFile file) throws IOException {

      /* » ... */
      List<Map<String, String>> metaFields = super.service.findMetaTablaDinamicaByNombre(nombreTabla);
      metaFields = this.filterMetaFieldsByExtraccion(metaFields);/* » Actualiza variable con campos de extraccion ... */
      String metaFieldsNameCsv = this.convertMetaFieldsToCsv(metaFields);/*
                                                                        * » Campos separados por comas, para el select
                                                                        * ...
                                                                        */
      int totalFieldsOfModel = metaFields.size();

      StringBuilder queryString = new StringBuilder();

      try (XSSFWorkbook book = new XSSFWorkbook(file.getInputStream())) {
         XSSFSheet sheet = book.getSheetAt(0);

         /* » Validar: Catidad de campos */
         XSSFRow row = sheet.getRow(0);
         int totalFieldsOfFile = row.getPhysicalNumberOfCells();
         if (totalFieldsOfModel != totalFieldsOfFile)
            throw new CreateTableWarning(Messages.WARNING_UPLOAD_TABLE(file.getOriginalFilename()));

         /* » QUERY-STRING ... */
         // ---------------------------------------------------------------------------------------------------------
         int totalRows = sheet.getPhysicalNumberOfRows();
         for (int r = 1; r < totalRows; r++) {
            row = sheet.getRow(r);/* » Fila de origen de datos ... */

            queryString.append("INSERT INTO ").append(nombreTabla)
                  .append("(").append(metaFieldsNameCsv).append(") VALUES(");

            for (int f = 0; f < totalFieldsOfModel; f++) {
               Cell cell = row.getCell(f);
               String fieldName = metaFields.get(f).get("nombre");
               if (totalFieldsOfModel == f + 1)/* » Si el cursor, lee el ultimo campo ... */
                  queryString.append(this.getCellValue(cell, fieldName)).append(") ");
               else
                  queryString.append(this.getCellValue(cell, fieldName)).append(", ");
            }
         }
      }

      return ResponseEntity.ok().body(this.service.saveTablaDinamica(nombreTabla, queryString.toString()));
   }

   @PutMapping(path = { "/alterTablaDinamica" })
   public ResponseEntity<?> alterTablaDinamica(@RequestBody TablaDinamicaDto tablaDinamica) {

      String nombreTabla = tablaDinamica.getNombre();
      String alterType = tablaDinamica.getAlterTableType();

      /*» Metadatos de tabla: Nombre y Tipo ...  */
      List<Map<String, String>> metaFields = super.service.findMetaTablaDinamicaByNombre(nombreTabla);

      StringBuilder queryString = new StringBuilder();/*» Query string ... */

      switch (alterType) {/*» Por tipo de modificación de tabla ... */
         case AlterTableType.ADD_COLUMN:
            for(String field : tablaDinamica.getCamposCsv().split(",")){

               String fieldName = field.trim().split(" ")[0].toString().trim();/*» Primera posición el nombre del campo ... */

               metaFields.stream().forEach(f -> {/*» Validación: Si nombre de campo existe en tabla... */
                  if(f.get("nombre").equals(fieldName))
                     throw new CreateTableWarning(Messages.WARNING_ALTER_TABLE_ADD_COLUMN(fieldName));
               });
               
               queryString
                     .append("ALTER TABLE ").append(nombreTabla)
                     .append(" ADD ").append(field).append(" NULL ");
            }
            
            break;
         case AlterTableType.DROP_COLUMN:
            queryString
               .append("ALTER TABLE ").append(nombreTabla)
               .append(" DROP COLUMN ").append(tablaDinamica.getCamposCsv().split(" ")[0].toString().trim());
         break;
      }

      /*» Alter table ... */
      super.service.alterTablaDinamica(queryString.toString());

      /*» Metadatos de tabla: Nombre y Tipo ...  */
      metaFields = super.service.findMetaTablaDinamicaByNombre(nombreTabla);

      return ResponseEntity.ok().body(
                                    Response
                                       .builder()
                                       .message(Messages.SUCCESS_ALTER_TABLE(nombreTabla))
                                       .data(this.filterMetaFieldsByExtraccion(metaFields))
                                       .build());
   }   

   // #region

   private Object getCellValue(Cell cell, String fieldName) {
      char tipoCampo = fieldName.trim().charAt(0);/* » Prefix de campo ... */
      switch (tipoCampo) {
         case 'd':
            return "'".concat(cell.getDateCellValue().toString()).concat("'");
         default:
            cell.setCellType(CellType.STRING);
            return "'".concat(cell.getStringCellValue()).concat("'");
      }
   }

   private List<Map<String, String>> filterMetaFieldsByExtraccion(List<Map<String, String>> metaFields) {
      return metaFields
            .stream()
            .filter(field -> field.get("nombre").endsWith("_e"))
            .collect(Collectors.toList());
   }

   private String convertMetaFieldsToCsv(List<Map<String, String>> metaFields) {
      return metaFields
            .stream()
            .map(field -> field.get("nombre"))
            .collect(Collectors.joining(", "));
   }

   // #endregion

}