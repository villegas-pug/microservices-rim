package com.microservicios.pais.services;

import java.util.Optional;
import com.commons.utils.models.entities.Pais;
import com.commons.utils.services.CommonServiceImpl;
import com.microservicios.pais.models.repository.PaisRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaisServiceImp extends CommonServiceImpl<Pais, PaisRepository> implements PaisService {

   @Override
   @Transactional(readOnly = true)
   public Optional<Pais> findByNacionalidad(String nacionalidad) {
      return super.repository.findByNacionalidad(nacionalidad);
   }

}
