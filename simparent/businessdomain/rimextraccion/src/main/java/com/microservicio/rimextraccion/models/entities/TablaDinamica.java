package com.microservicio.rimextraccion.models.entities;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "RimTablaDinamica")
@Data
@Builder(builderClassName = "TablaDinamicaBuilder", builderMethodName = "of", buildMethodName = "get")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "idTabla" })
public class TablaDinamica implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "nIdTabla")
   private Long idTabla;
   
   @Column(name = "sNombre", length = 55, nullable = false)
   private String nombre;
   
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "uIdUsrCreador", nullable = false)
   private Usuario usrCreador;
   
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "dFechaCreacion", nullable = false)
   private Date fechaCreacion;
   
   @Column(name = "bActivo", nullable = false)
   private boolean activo;

   @PrePersist
   private void prePersist(){
      this.fechaCreacion = new Date();
      this.activo = true;
   }

   /*
   *
   */
   private static final long serialVersionUID = 1L;
   
}