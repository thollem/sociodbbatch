package com.artsgard.sociodbbatch.processors;

import com.artsgard.sociodbbatch.model.Address;
import com.artsgard.sociodbbatch.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.batch.item.ItemProcessor;

/**
 *
 * @author artsgard
 */
@Component
public class AddressProcessor implements ItemProcessor<Address, Address> {

    @Autowired
    private AddressRepository repo;

    @Override
    public Address process(Address address) throws Exception {
        
        System.out.println(address.getStreet());
        address.setStreet(address.getStreet() + "-cvc");
        return address;
    }
}