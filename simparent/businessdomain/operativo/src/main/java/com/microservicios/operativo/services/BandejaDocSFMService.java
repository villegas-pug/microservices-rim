package com.microservicios.operativo.services;

import java.util.List;
import com.microservicios.operativo.models.entities.BandejaDocSFM;

public interface BandejaDocSFMService {

   void save(BandejaDocSFM bandejaDocSFM);

   void saveAll(List<BandejaDocSFM> lstBandejaDocSFM);

   List<BandejaDocSFM> findAll();

   

}