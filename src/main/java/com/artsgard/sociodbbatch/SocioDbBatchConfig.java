package com.artsgard.sociodbbatch;

import com.artsgard.sociodbbatch.model.SocioAssociatedSocio;
import com.artsgard.sociodbbatch.writers.SocioActiveWriter;
import com.artsgard.sociodbbatch.readers.SocioAcitveReader;
import com.artsgard.sociodbbatch.model.com.artsgard.sociodbbatch.processors.SocioActiveProcessor;
import com.artsgard.sociodbbatch.model.SocioModel;
import com.artsgard.sociodbbatch.model.com.artsgard.sociodbbatch.processors.SocioPendingProcessor;
import com.artsgard.sociodbbatch.readers.SocioPendingReader;
import com.artsgard.sociodbbatch.writers.SocioPendingWriter;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author artsgard
 */
@Configuration
@EnableBatchProcessing
public class SocioDbBatchConfig extends DefaultBatchConfigurer {

    @Autowired
    private DataSource datasource;

    @Autowired
    private JobBuilderFactory jobBuilders;

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

    @Bean
    public Job customerReportJob() throws Exception {
        return jobBuilders.get("socioDbBatchJob")
                .start(chunkStep1())
                .next(chunkStep2())
                .build();
    }

    @Bean
    public Step chunkStep1() throws Exception {
        return stepBuilders.get("socioBatchStep1")
                .<SocioModel, SocioModel>chunk(20)
                .reader(activeReader.itemReader(datasource))
                .processor(activeProcessor)
                .writer(activeWriter)
                .build();
    }
    
    @Bean
    public Step chunkStep2() throws Exception {
        return stepBuilders.get("socioBatchStep1")
                .<SocioAssociatedSocio, SocioAssociatedSocio>chunk(20)
                .reader(pendingReader.itemReader(datasource))
                .processor(pendingProcessor)
                .writer(pendingWriter)
                .build();
    }

    @Override
    protected JobRepository createJobRepository() throws Exception {
        MapJobRepositoryFactoryBean factoryBean = new MapJobRepositoryFactoryBean();
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }
}
