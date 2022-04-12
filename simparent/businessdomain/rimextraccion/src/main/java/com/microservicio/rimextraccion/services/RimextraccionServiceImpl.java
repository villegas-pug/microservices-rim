package com.microservicio.rimextraccion.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.Tuple;
import com.commons.utils.services.CommonServiceImpl;
import com.microservicio.rimextraccion.helpers.DataModelHelper;
import com.microservicio.rimextraccion.models.entities.TablaDinamica;
import com.microservicio.rimextraccion.models.repository.RimextraccionRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RimextraccionServiceImpl extends CommonServiceImpl<TablaDinamica, RimextraccionRepository>
      implements RimextraccionService {

   @Override
   @Transactional
   public List<Tuple> createTable(String nombreTabla, String cuerpoTabla) {
      return super.repository.createTable(nombreTabla, cuerpoTabla);
   }

   @Override
   @Transactional(readOnly = true)
   public Optional<TablaDinamica> findByNombre(String nombre) {
      return super.repository.findByNombre(nombre);
   }

   @Override
   @Transactional(readOnly = true)
   public List<Map<String, String>> findMetaTablaDinamicaByNombre(String nombreTabla) {
      return DataModelHelper.convertMetaFields(super.repository.findMetaTablaDinamicaByNombre(nombreTabla));
   }

   @Override
   @Transactional
   public Long saveTablaDinamica(String nombreTabla, String sqlInsertValues) {
      return super.repository.saveTablaDinamica(nombreTabla, sqlInsertValues);
   }

   @Override
   @Transactional
   public Long alterTablaDinamica(String queryString) {
      return super.repository.alterTablaDinamica(queryString);
   }

}