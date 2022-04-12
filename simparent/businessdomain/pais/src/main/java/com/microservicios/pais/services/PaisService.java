package com.microservicios.pais.services;

import java.util.Optional;

import com.commons.utils.models.entities.Pais;
import com.commons.utils.services.CommonService;

public interface PaisService extends CommonService<Pais> {

   Optional<Pais> findByNacionalidad(String nacionalidad);
}
