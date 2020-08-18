package com.artsgard.sociodbbatch.repository;

import com.artsgard.sociodbbatch.model.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressModel, Long> {
  
}
