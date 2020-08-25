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
@EnableJpaRepositories(basePackages = {"com.artsgard.sociodbbatch.bank.repository", "com.artsgard.sociodbbatch.config" },
                entityManagerFactoryRef = "bankDbEntityManagerFactory", 
                transactionManagerRef = "bankDbTransactionManager")
public class BatchBankDbRepoConfig {
	
	@Bean(name = "bankDbDataSourceProperties")
	@ConfigurationProperties("app.datasource.bankdb")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "bankDbDataSource")
	@ConfigurationProperties("app.datasource.bankdb.hikari")
	public DataSource dataSource(@Qualifier("bankDbDataSourceProperties") DataSourceProperties dataSourceProperties) {
		return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class)
				.build();
	}
	
	@Bean(name = "bankDbEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			EntityManagerFactoryBuilder builder, @Qualifier("bankDbDataSource") DataSource dataSource) {
		return builder
                    .dataSource(dataSource)
                    .packages("com.artsgard.sociodbbatch.bank.model")
                    .persistenceUnit("bankdb")
                    .build();
	}

	@Bean(name = "bankDbTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("bankDbEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
