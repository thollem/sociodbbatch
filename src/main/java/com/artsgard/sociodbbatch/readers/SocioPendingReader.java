package com.artsgard.sociodbbatch.readers;

import com.artsgard.sociodbbatch.model.SocioAssociatedSocio;
import com.artsgard.sociodbbatch.model.SocioModel;
import javax.persistence.EntityManagerFactory;
import org.springframework.batch.item.ItemReader;
import javax.sql.DataSource;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author artsgard https://mkyong.com/tutorials/spring-batch-tutorial/
 * https://docs.spring.io/spring-batch/docs/current/reference/html/readersAndWriters.html
 */
@Component
public class SocioPendingReader {

    private static final String QUERY_FIND_ALL_ASSOCIATED_SOCIOS
            = "select a from SocioAssociatedSocio a";

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public ItemReader<SocioAssociatedSocio> itemReader(DataSource dataSource) {
        return new JpaPagingItemReaderBuilder<SocioAssociatedSocio>()
                .name("associated-socio-reader")
                .entityManagerFactory(entityManagerFactory)
                .queryString(QUERY_FIND_ALL_ASSOCIATED_SOCIOS)
                .pageSize(20)
                .build();
    }
}
