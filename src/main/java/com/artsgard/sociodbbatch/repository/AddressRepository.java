package com.artsgard.sociodbbatch.repository;

import com.artsgard.sociodbbatch.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
  
}
