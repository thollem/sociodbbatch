package com.artsgard.sociodbbatch.config;

import com.artsgard.sociodbbatch.bank.model.Account;
import com.artsgard.sociodbbatch.socio.model.SocioAssociatedSocio;
import com.artsgard.sociodbbatch.socio.model.SocioModel;
import com.artsgard.sociodbbatch.processors.AssociatedSocioProcessor;
import com.artsgard.sociodbbatch.processors.SocioAccountProcessor;
import com.artsgard.sociodbbatch.processors.SocioProcessor;
import com.artsgard.sociodbbatch.readers.AssociatedSocioReader;
import com.artsgard.sociodbbatch.readers.SocioReader;
import com.artsgard.sociodbbatch.writers.AccountWriter;
import com.artsgard.sociodbbatch.writers.AssociatedSocioWriter;
import com.artsgard.sociodbbatch.writers.SocioWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *
 * @author artsgard
 */
@Configuration
@EnableBatchProcessing
public class BatchFlowConfig {
    
    @Autowired
    @Qualifier("socioDbTransactionManager") 
    private PlatformTransactionManager socioTransactionManager;
    
    @Autowired
    @Qualifier("bankDbTransactionManager") 
    private PlatformTransactionManager bankTransactionManager;
    
    @Autowired
    private JobBuilderFactory jobBuilders;

    @Autowired
    private StepBuilderFactory stepBuilders;
    
    @Autowired
    private JobRepository jobRepository;
    
    @Autowired
    private SocioProcessor socioProcessor;

    @Autowired
    private SocioReader socioReader;

    @Autowired
    private SocioWriter socioWriter;
    
    @Autowired
    private AssociatedSocioProcessor associatedProcessor;

    @Autowired
    private AssociatedSocioReader associatedReader;

    @Autowired
    private AssociatedSocioWriter associatedWriter;
    
    @Autowired
    private SocioAccountProcessor socioAccountProcessor;

    @Autowired
    private AccountWriter accountWriter;

    @Bean(name = "sociojob")
    public Job userDbJob() throws Exception {
        return jobBuilders.get("batchdbsocioJob")
                .repository(jobRepository)
                .start(socioStep())
                .next(associatedSocioStep())
                .next(accountStep())
                .build();
    }

    @Bean
    public Step socioStep() throws Exception {
        return stepBuilders.get("batchdbsocioStep-socio")
                .<SocioModel, SocioModel>chunk(20)
                .reader(socioReader)
                .processor(socioProcessor)
                .writer(socioWriter)
                .transactionManager(socioTransactionManager)
                .build();
    }
 
    @Bean
    public Step associatedSocioStep() throws Exception {
        return stepBuilders.get("batchdbsocioStep-associated")
                .<SocioAssociatedSocio, SocioAssociatedSocio>chunk(20)
                .reader(associatedReader)
                .processor(associatedProcessor)
                .writer(associatedWriter)
                .transactionManager(socioTransactionManager)
                .build();
    }
    
    @Bean
    public Step accountStep() throws Exception {
        return stepBuilders.get("batchdbsocioStep-account")
                .<SocioModel, Account>chunk(20)
                .reader(socioReader)
                .processor(socioAccountProcessor)
                .writer(accountWriter)
                .transactionManager(bankTransactionManager)
                .build();
    }

}
