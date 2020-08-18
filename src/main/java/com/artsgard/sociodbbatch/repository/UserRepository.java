package com.artsgard.sociodbbatch.repository;

import com.artsgard.sociodbbatch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  
}
