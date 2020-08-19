package com.artsgard.sociodbbatch.config;


import com.artsgard.sociodbbatch.model.SocioAssociatedSocio;
import com.artsgard.sociodbbatch.model.SocioModel;
import com.artsgard.sociodbbatch.processors.AssociatedSocioProcessor;
import com.artsgard.sociodbbatch.processors.SocioProcessor;
import com.artsgard.sociodbbatch.readers.AssociatedSocioReader;
import com.artsgard.sociodbbatch.readers.SocioReader;
import com.artsgard.sociodbbatch.writers.AssociatedSocioWriter;
import com.artsgard.sociodbbatch.writers.SocioWriter;
import javax.sql.DataSource;
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
    @Qualifier("dbTransactionManager") 
    private PlatformTransactionManager transactionManager;
    
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

    @Bean(name = "sociojob")
    public Job userDbJob() throws Exception {
        return jobBuilders.get("batchdbsociowriteJob")
                .repository(jobRepository)
                .start(socioStep())
                .next(associatedSocioStep())
                .build();
    }

    @Bean
    public Step socioStep() throws Exception {
        return stepBuilders.get("sociobatchdbsociowriteStep")
                .<SocioModel, SocioModel>chunk(20)
                .reader(socioReader)
                .processor(socioProcessor)
                .writer(socioWriter)
                .transactionManager(transactionManager)
                .build();
    }
    
    @Bean
    public Step associatedSocioStep() throws Exception {
        return stepBuilders.get("associatedsociodbsociowriteStep")
                .<SocioAssociatedSocio, SocioAssociatedSocio>chunk(20)
                .reader(associatedReader)
                .processor(associatedProcessor)
                .writer(associatedWriter)
                .transactionManager(transactionManager)
                .build();
    }
}
