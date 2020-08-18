package com.artsgard.sociodbbatch.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 *
 * @author artsgard
 * 
 */
@Configuration
public class BatchDbMetaDataRepoConfig {
    
    @Primary
    @Bean(name = "batchDataSourceProperties")
    @ConfigurationProperties("app.datasource.batch")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "batchDataSource")
    @ConfigurationProperties("app.datasource.batch.hikari")
    public DataSource dataSource(@Qualifier("batchDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }
}
