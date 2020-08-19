package com.artsgard.sociodbbatch.repository;

import com.artsgard.sociodbbatch.model.SocioAssociatedSocio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociatedSocioRepository extends JpaRepository<SocioAssociatedSocio, Long> {
  
}
