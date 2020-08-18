package com.artsgard.sociodbbatch.readers;

import com.artsgard.sociodbbatch.model.Address;
import javax.persistence.EntityManagerFactory;
import org.springframework.batch.item.ItemReader;
import javax.sql.DataSource;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 
 * @author artsgard
 */
@Component
public class AddressReader {
    private static final String QUERY_FIND_ALL_ADDRESSES = "select a from Address a";

    @Autowired
    @Qualifier("dbEntityManagerFactory")
    private EntityManagerFactory entityManagerFactory;

    public ItemReader<Address> itemReader(DataSource dataSource) {
        return new JpaPagingItemReaderBuilder<Address>()
                .name("address-reader")
                .entityManagerFactory(entityManagerFactory)
                .queryString(QUERY_FIND_ALL_ADDRESSES)
                .pageSize(20)
                .build();
    }
}
