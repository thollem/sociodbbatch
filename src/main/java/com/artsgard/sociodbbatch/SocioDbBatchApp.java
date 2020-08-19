package com.artsgard.sociodbbatch;

import com.artsgard.sociodbbatch.model.SocioAssociatedSocio;
import com.artsgard.sociodbbatch.repository.AssociatedSocioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
