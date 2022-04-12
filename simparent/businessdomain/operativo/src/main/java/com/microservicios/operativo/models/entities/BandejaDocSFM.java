package com.microservicios.operativo.models.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.commons.utils.constants.Estados;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SidBandejaDocSFM")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "BandejaDocSFMBuilder", builderMethodName = "of", buildMethodName = "get")
@EqualsAndHashCode(of = { "idBandejaDoc" })
public class BandejaDocSFM implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "nIdBandejaDoc")
   private Long idBandejaDoc;
   
   @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinColumn(name = "idVerifExp", nullable = true)
   private EvaluarTramiteSFM evaluarTramite;

   /* private Etapa */

   @Column(name = "sTipoDocumento", length = 50)
   private String tipoDocumento;

   @Column(name = "sNumeroDocumento", length = 150)
   private String numeroDocumento;
   
   @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
   @Temporal(TemporalType.DATE)
   @Column(name = "dFechaDocumento", nullable = false)
   private Date fechaDocumento;

   @Column(name = "sNumeroExpediente", length = 55)
   private String numeroExpediente;
   
   @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
   @Temporal(TemporalType.DATE)
   @Column(name = "dFechaInicioTramite")
   private Date fechaInicioTramite;
   
   @Column(name = "sProcedimiento", length = 100)
   private String procedimiento;
   
   @Column(name = "sSubtipoProcedimiento", length = 100)
   private String subtipoProcedimiento;

   @Column(name = "sAdministrado", length = 150)
   private String administrado;

   @Column(name = "sTipoDocumentoIdentidad", length = 55)
   private String tipoDocumentoIdentidad;
   
   @Column(name = "sNumeroDocIdentidad", length = 100)
   private String numeroDocIdentidad;
   
   @Column(name = "sNacionalidad", length = 50)
   private String nacionalidad;
   
   @Column(name = "sDomicilio", length = 200)
   private String domicilio;
   
   @Column(name = "sDistrito", length = 55)
   private String distrito;

   @Column(name = "sEstado", nullable = false)
   private String estado;
   
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "dFechaHoraRegistro", nullable = false)
   private Date fechaHoraRegistro;

   @PrePersist
   private void prePersist(){
      this.fechaHoraRegistro = new Date();
      this.estado = Estados.PENDIENTE;
   }
   
   /**
    * 
    */
   private static final long serialVersionUID = 1L; 
}