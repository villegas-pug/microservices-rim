package com.microservicio.rimextraccion.dto;

import java.util.Date;
import com.commons.utils.models.entities.Usuario;
import lombok.Data;

@Data
public class TablaDinamicaDto {
 
   private Long idTabla;
   private String nombre;
   private Usuario usrCreador;
   private Date fechaCreacion;
   private boolean activo;
   private String camposCsv;
   private String alterTableType;
   
}