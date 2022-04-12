package com.microservicios.operativo.controllers;


import java.util.List;
import com.commons.utils.constants.Messages;
import com.commons.utils.errors.DataAccessEmptyWarning;
import com.commons.utils.utils.Response;
import com.microservicios.operativo.models.entities.BandejaDocSFM;
import com.microservicios.operativo.services.BandejaDocSFMService;
import com.microservicios.operativo.services.TipoTramiteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin( origins = { "*" } )
@RestController
public class BandejaDocSFMController {
   
   @Autowired
   private BandejaDocSFMService service;

   @Autowired
   private TipoTramiteService tipoTramiteService;

   @PostMapping( path = { "/saveBandejaDoc" }, consumes = { MediaType.APPLICATION_JSON_VALUE } )
   public ResponseEntity<?> saveBandejaDoc(@RequestBody BandejaDocSFM bandejaDocSFM){

      this.service.save(bandejaDocSFM);

      List<BandejaDocSFM> bandejaDocSFMDb = this.service.findAll();
      if (bandejaDocSFMDb.size() == 0) throw new DataAccessEmptyWarning();

      return ResponseEntity.ok().body(
                                    Response
                                       .builder()
                                       .message(Messages.MESSAGE_SUCCESS_SAVE("Solicitud"))
                                       .data(bandejaDocSFMDb)
                                       .build());
   }

   @GetMapping( path = { "/findAllTipoTramite" } )
   public ResponseEntity<?> findAllTipoTramite() {
       return ResponseEntity.ok().body(
                                    Response
                                       .builder()
                                       .message(Messages.MESSAGE_SUCCESS_LIST_ENTITY)
                                       .data(this.tipoTramiteService.findAll())
                                       .build());
   }

}