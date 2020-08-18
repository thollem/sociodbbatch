package com.artsgard.sociodbbatch.config;


import com.artsgard.sociodbbatch.model.Address;
import com.artsgard.sociodbbatch.model.User;
import com.artsgard.sociodbbatch.processors.AddressProcessor;
import com.artsgard.sociodbbatch.processors.UserProcessor;
import com.artsgard.sociodbbatch.readers.AddressReader;
import com.artsgard.sociodbbatch.readers.UserReader;
import com.artsgard.sociodbbatch.writers.AddressWriter;
import com.artsgard.sociodbbatch.writers.UserWriter;
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
    private UserProcessor userProcessor;

    @Autowired
    private UserReader userReader;

    @Autowired
    private UserWriter userWriter;
    
    @Autowired
    private AddressProcessor addressProcessor;

    @Autowired
    private AddressReader addressReader;

    @Autowired
    private AddressWriter addressWriter;

    @Bean(name = "userjob")
    public Job userDbJob() throws Exception {
        return jobBuilders.get("batchdbsociowriteJob")
                .repository(jobRepository)
                .start(userStep1())
                .next(userStep2())
                .build();
    }

    @Bean
    public Step userStep1() throws Exception {
        return stepBuilders.get("userbatchdbsociowriteStep")
                .<User, User>chunk(20)
                .reader(userReader.itemReader(dataSource))
                .processor(userProcessor)
                .writer(userWriter)
                .transactionManager(transactionManager)
                .build();
    }
    
    @Bean
    public Step userStep2() throws Exception {
        return stepBuilders.get("addressbatchdbsociowriteStep")
                .<Address, Address>chunk(20)
                .reader(addressReader.itemReader(dataSource))
                .processor(addressProcessor)
                .writer(addressWriter)
                .transactionManager(transactionManager)
                .build();
    }
}
