package com.artsgard.sociodbbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * @author artsgard
 */
@SpringBootApplication
@EnableScheduling
public class SocioDbBatchApp {
    public static void main(String[] args) {
        SpringApplication.run(SocioDbBatchApp.class, args);
    }
}
