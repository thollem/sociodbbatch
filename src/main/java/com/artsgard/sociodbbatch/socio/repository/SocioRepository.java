package com.artsgard.sociodbbatch.socio.repository;

import com.artsgard.sociodbbatch.socio.model.SocioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocioRepository extends JpaRepository<SocioModel, Long> {
  
}
