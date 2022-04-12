package com.commons.utils.models.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SidEtapa")
@Data
@Builder(builderClassName = "EtapaBuilder", builderMethodName = "of", buildMethodName = "get")
@NoArgsConstructor
@AllArgsConstructor
public class Etapa implements Serializable {

   @Id
   @Column(name = "nIdEtapa")
   private Long idEtapa;
   
   @Column(name = "sDescripcion", length = 100, nullable = false)
   private String descripcion;
   
   @Column(name = "bActivo", nullable = false)
   private boolean activo;
   
   @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "America/Lima")
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "dFechaHoraAud", length = 100, nullable = false)
   private Date fechaHoraAud;

   @PrePersist
   private void prePersist(){
      this.activo = true;
      this.fechaHoraAud = new Date();
   }

   /*
   *
   */
   private static final long serialVersionUID = 1L;
   
}