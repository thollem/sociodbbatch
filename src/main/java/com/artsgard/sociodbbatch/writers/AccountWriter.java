package com.artsgard.sociodbbatch.writers;

import com.artsgard.sociodbbatch.bank.model.Account;
import com.artsgard.sociodbbatch.bank.repository.AccountRepository;
import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.artsgard.sociodbbatch.socio.repository.SocioRepository;

/**
 *
 * @author artsgard
 */

@Component
public class AccountWriter implements ItemWriter<Account> {
    
    @Autowired
    private AccountRepository repo;
    
    @Override
    public void write(List<? extends Account> accounts) throws Exception {
        repo.saveAll(accounts);
    }
}
