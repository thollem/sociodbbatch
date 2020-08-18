package com.artsgard.sociodbbatch.writers;

import com.artsgard.sociodbbatch.model.Address;
import com.artsgard.sociodbbatch.repository.AddressRepository;
import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author artsgard
 */

@Component
public class AddressWriter implements ItemWriter<Address> {
    
    @Autowired
    private AddressRepository repo;
    
    @Override
    public void write(List<? extends Address> addresses) throws Exception {
        repo.saveAll(addresses);
    }
}
