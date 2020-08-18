package com.artsgard.sociodbbatch.repository;

import com.artsgard.sociodbbatch.model.SocioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocioRepository extends JpaRepository<SocioModel, Long> {
  
}
