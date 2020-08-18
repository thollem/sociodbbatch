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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.artsgard.sociodbbatch.repository", "com.artsgard.sociodbbatch.config" },
                entityManagerFactoryRef = "dbEntityManagerFactory", 
                transactionManagerRef = "dbTransactionManager")

public class BatchDbRepoConfig {
	
	@Bean(name = "dbDataSourceProperties")
	@ConfigurationProperties("app.datasource.db")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "dbDataSource")
	@ConfigurationProperties("app.datasource.db.hikari")
	public DataSource dataSource(@Qualifier("dbDataSourceProperties") DataSourceProperties dataSourceProperties) {
		return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class)
				.build();
	}
	
	@Bean(name = "dbEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			EntityManagerFactoryBuilder builder, @Qualifier("dbDataSource") DataSource dataSource) {
		return builder
				.dataSource(dataSource)
				.packages("com.artsgard.sociodbbatch.model")
				.persistenceUnit("db")
				.build();
	}

	@Bean(name = "dbTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("dbEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
