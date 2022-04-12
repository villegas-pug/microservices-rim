package com.commons.utils.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public class CommonServiceImpl<E, R extends JpaRepository<E, Long>> implements CommonService<E> {

   @Autowired
   protected R repository;

   @Transactional(readOnly = true)
   @Override
   public List<E> findAll() {
      return repository.findAll();
   }

   @Transactional(readOnly = true)
   @Override
   public Optional<E> findById(Long id) {
      return repository.findById(id);
   }

   @Transactional
   @Override
   public E save(E entity) {
      return repository.save(entity);
   }

   @Transactional
   @Override
   public void deleteById(Long id) {
      repository.deleteById(id);
   }

   @Transactional(readOnly = true)
   @Override
   public Long count() {
      return repository.count();
   }
}
