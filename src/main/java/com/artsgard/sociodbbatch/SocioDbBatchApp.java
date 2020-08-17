package com.artsgard.sociodbbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * @author artsgard
 */
@SpringBootApplication //(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
    //DataSourceTransactionManagerAutoConfiguration.class })
@EnableScheduling
public class SocioDbBatchApp {

    public static void main(String[] args) {
        SpringApplication.run(SocioDbBatchApp.class, args);
    }
}
