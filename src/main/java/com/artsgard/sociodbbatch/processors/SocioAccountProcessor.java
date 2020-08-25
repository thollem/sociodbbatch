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
import java.text.SimpleDateFormat;

/**
 *
 * @author artsgard
 */
@Component
public class SocioAccountProcessor implements ItemProcessor<SocioModel, Account> {

    @Override
    public Account process(SocioModel socio) throws Exception {
        Timestamp now = new Timestamp(System.currentTimeMillis());
       
        SimpleDateFormat dayformat = new SimpleDateFormat("yyyyMMdd");
        // add a new socio to the bank of today only (with a 20euro bonus)
        if (dayformat.format(socio.getRegisterDate()).equals(dayformat.format(now))) {
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
