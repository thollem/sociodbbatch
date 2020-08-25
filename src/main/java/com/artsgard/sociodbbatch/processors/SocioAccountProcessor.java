package com.artsgard.sociodbbatch.processors;

import com.artsgard.sociodbbatch.bank.model.Account;
import com.artsgard.sociodbbatch.bank.repository.AccountRepository;
import com.artsgard.sociodbbatch.socio.model.SocioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.batch.item.ItemProcessor;
import com.artsgard.sociodbbatch.socio.repository.SocioRepository;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 * @author artsgard
 */
@Component
public class SocioAccountProcessor implements ItemProcessor<SocioModel, Account> {

    @Autowired
    private SocioRepository repo;

    @Autowired
    private AccountRepository accountRepo;

    @Override
    public Account process(SocioModel socio) throws Exception {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Calendar calNow = Calendar.getInstance();
        calNow.setTimeInMillis(now.getTime());

        //if (socio.getRegisterDate().after(now)) {
        if (socio.getUsername().equals("rw")) {
            Account account = new Account();
            account.setIban("iban-" + socio.getUsername());
            account.setUsername(socio.getUsername());
            account.setCurrency("EUR");
            account.setBalance(new BigDecimal("20.00"));
            account.setActive(true);
            account.setCreationDate(now);
            return account;
        } else {
            return null;
        }
    }
}
