package com.artsgard.sociodbbatch.bank.repository;

import com.artsgard.sociodbbatch.bank.model.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByIban(String iban);
    Optional<Account> findByUsername(String username);
}
