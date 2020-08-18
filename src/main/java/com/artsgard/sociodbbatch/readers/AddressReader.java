package com.artsgard.sociodbbatch.readers;

import com.artsgard.sociodbbatch.model.AddressModel;
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
    private static final String QUERY_FIND_ALL_ADDRESSES = "select a from AddressModel a";

    @Autowired
    @Qualifier("dbEntityManagerFactory")
    private EntityManagerFactory entityManagerFactory;

    public ItemReader<AddressModel> itemReader(DataSource dataSource) {
        return new JpaPagingItemReaderBuilder<AddressModel>()
                .name("address-reader")
                .entityManagerFactory(entityManagerFactory)
                .queryString(QUERY_FIND_ALL_ADDRESSES)
                .pageSize(20)
                .build();
    }
}
