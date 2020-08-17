package com.artsgard.sociodbbatch.config;

import com.artsgard.sociodbbatch.model.SocioAssociatedSocio;
import com.artsgard.sociodbbatch.model.SocioModel;
import com.artsgard.sociodbbatch.processors.SocioActiveProcessor;
import com.artsgard.sociodbbatch.processors.SocioPendingProcessor;
import com.artsgard.sociodbbatch.readers.SocioAcitveReader;
import com.artsgard.sociodbbatch.readers.SocioPendingReader;
import com.artsgard.sociodbbatch.writers.SocioActiveWriter;
import com.artsgard.sociodbbatch.writers.SocioPendingWriter;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
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
    @Qualifier("dbDataSource") 
    private DataSource datasource;

    @Autowired
    private JobBuilderFactory jobBuilders;
    
    @Autowired
    @Qualifier("dbTransactionManager") 
    private PlatformTransactionManager transactionManager;

    @Autowired
    private StepBuilderFactory stepBuilders;

    @Autowired
    private SocioActiveProcessor activeProcessor;

    @Autowired
    private SocioAcitveReader activeReader;

    @Autowired
    private SocioActiveWriter activeWriter;
    
    @Autowired
    private SocioPendingProcessor pendingProcessor;

    @Autowired
    private SocioPendingReader pendingReader;

    @Autowired
    private SocioPendingWriter pendingWriter;

    @Bean("sociojob")
    public Job socioJob() throws Exception {
        return jobBuilders.get("socioDbBatchJob")
                .start(socioStep1())
                .next(socioStep2())
                .build();
    }

    @Bean
    public Step socioStep1() throws Exception {
        return stepBuilders.get("socioDbBatchStepActive")
                .<SocioModel, SocioModel>chunk(20)
                .reader(activeReader.itemReader(datasource))
                .processor(activeProcessor)
                .writer(activeWriter)
                .transactionManager(transactionManager)
                .build();
    }
   
    @Bean
    public Step socioStep2() throws Exception {
        return stepBuilders.get("socioDbBatchStepPending")
                .<SocioAssociatedSocio, SocioAssociatedSocio>chunk(20)
                .reader(pendingReader.itemReader(datasource))
                .processor(pendingProcessor)
                .writer(pendingWriter)
                .transactionManager(transactionManager)
                .build();
    }
}
