package com.artsgard.sociodbbatch.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import com.zaxxer.hikari.HikariDataSource;
import javax.persistence.EntityManagerFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author artsgard
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.artsgard.sociodbbatch.socio.repository", "com.artsgard.sociodbbatch.config" },
                entityManagerFactoryRef = "socioDbEntityManagerFactory", 
                transactionManagerRef = "socioDbTransactionManager")
public class BatchSocioDbRepoConfig {
	
	@Bean(name = "socioDbDataSourceProperties")
	@ConfigurationProperties("app.datasource.sociodb")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "socioDbDataSource")
	@ConfigurationProperties("app.datasource.sociodb.hikari")
	public DataSource dataSource(@Qualifier("socioDbDataSourceProperties") DataSourceProperties dataSourceProperties) {
		return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class)
				.build();
	}
	
	@Bean(name = "socioDbEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			EntityManagerFactoryBuilder builder, @Qualifier("socioDbDataSource") DataSource dataSource) {
		return builder
                    .dataSource(dataSource)
                    .packages("com.artsgard.sociodbbatch.socio.model")
                    .persistenceUnit("sociodb")
                    .build();
	}

	@Bean(name = "socioDbTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("socioDbEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
