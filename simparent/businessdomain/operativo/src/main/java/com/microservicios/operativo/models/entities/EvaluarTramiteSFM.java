package com.microservicios.operativo.models.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.commons.utils.models.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SidEvaluarTramiteSFM")
@Data
@EqualsAndHashCode( of = { "idVerifExp" } )
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "EvaluarTramiteSFMBuilder", builderMethodName = "of", buildMethodName = "get")
public class EvaluarTramiteSFM implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "nIdVerifExp")
   private Long idVerifExp;
   
   @Temporal(TemporalType.DATE)
   @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
   @Column(name = "dFechaRegistro", nullable = false)
   private Date fechaRegistro;
   
   @Temporal(TemporalType.DATE)
   @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
   @Column(name = "dFechaDerivacion", nullable = false)
   private Date fechaDerivacion;
   
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "uIdOperadorDesig", nullable = false)
   private Usuario operadorDesig;
   
   @Temporal(TemporalType.DATE)
   @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
   @Column(name = "dFechaTermino")
   private Date fechaTermino;
   
   @Column(name = "bLeido", nullable = false)
   private boolean leido;
   
   @Column(name = "bActivo", nullable = false)
   private boolean activo;
   
   @Column(name = "bCompletado", nullable = false)
   private boolean completado;
   
   @Column(name = "sObservacion", length = 500)
   private String sObservacion;

   @PrePersist
   private void prePersist(){
      this.fechaRegistro = new Date();
      this.fechaDerivacion = new Date();
      this.leido = false;
      this.completado = false;
      this.activo = true;
   }

   /*
   * 
   */
   private static final long serialVersionUID = 1L;

}