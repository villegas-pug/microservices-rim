package com.microservicio.rimextraccion.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.Tuple;

import com.commons.utils.services.CommonService;
import com.microservicio.rimextraccion.models.entities.TablaDinamica;

public interface RimextraccionService extends CommonService<TablaDinamica> {
   
   List<Tuple> createTable(String nombreTabla, String cuerpoTabla);
   Optional<TablaDinamica> findByNombre(String nombre);
   List<Map<String, String>> findMetaTablaDinamicaByNombre(String nombreTabla);
   Long saveTablaDinamica(String nombreTabla, String sqlInsertValues);
   Long alterTablaDinamica(String queryString);
}