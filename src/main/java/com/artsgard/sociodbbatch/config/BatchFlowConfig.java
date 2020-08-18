package com.artsgard.sociodbbatch.config;


import com.artsgard.sociodbbatch.model.AddressModel;
import com.artsgard.sociodbbatch.model.SocioModel;
import com.artsgard.sociodbbatch.processors.AddressProcessor;
import com.artsgard.sociodbbatch.processors.SocioProcessor;
import com.artsgard.sociodbbatch.readers.AddressReader;
import com.artsgard.sociodbbatch.readers.SocioReader;
import com.artsgard.sociodbbatch.writers.AddressWriter;
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
    @Qualifier("dbDataSource")
    private DataSource dataSource;
    
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
    private AddressProcessor addressProcessor;

    @Autowired
    private AddressReader addressReader;

    @Autowired
    private AddressWriter addressWriter;

    @Bean(name = "sociojob")
    public Job userDbJob() throws Exception {
        return jobBuilders.get("batchdbsociowriteJob")
                .repository(jobRepository)
                .start(socioStep())
                .next(addressStep())
                .build();
    }

    @Bean
    public Step socioStep() throws Exception {
        return stepBuilders.get("sociobatchdbsociowriteStep")
                .<SocioModel, SocioModel>chunk(20)
                .reader(socioReader.itemReader(dataSource))
                .processor(socioProcessor)
                .writer(socioWriter)
                .transactionManager(transactionManager)
                .build();
    }
    
    @Bean
    public Step addressStep() throws Exception {
        return stepBuilders.get("addressbatchdbsociowriteStep")
                .<AddressModel, AddressModel>chunk(20)
                .reader(addressReader.itemReader(dataSource))
                .processor(addressProcessor)
                .writer(addressWriter)
                .transactionManager(transactionManager)
                .build();
    }
}
