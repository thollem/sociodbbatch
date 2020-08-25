package com.artsgard.sociodbbatch.socio.repository;

import com.artsgard.sociodbbatch.socio.model.SocioAssociatedSocio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociatedSocioRepository extends JpaRepository<SocioAssociatedSocio, Long> {
  
}
