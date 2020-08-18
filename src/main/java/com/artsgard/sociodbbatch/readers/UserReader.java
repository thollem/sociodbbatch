package com.artsgard.sociodbbatch.readers;

import com.artsgard.sociodbbatch.model.User;
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
public class UserReader {
    private static final String QUERY_FIND_ALL_USERS = "select a from User a";

    @Autowired
    @Qualifier("dbEntityManagerFactory")
    private EntityManagerFactory entityManagerFactory;

    public ItemReader<User> itemReader(DataSource dataSource) {
        return new JpaPagingItemReaderBuilder<User>()
                .name("user-reader")
                .entityManagerFactory(entityManagerFactory)
                .queryString(QUERY_FIND_ALL_USERS)
                .pageSize(20)
                .build();
    }
}
