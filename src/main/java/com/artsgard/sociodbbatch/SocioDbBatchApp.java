package com.artsgard.sociodbbatch;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author artsgard
 */
@SpringBootApplication
@EnableScheduling
@PropertySource({"classpath:application.properties", "classpath:postgres.properties"})

public class SocioDbBatchApp {

    private static final Logger log = LoggerFactory.getLogger(SocioDbBatchApp.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    public static final int ONE_SINGLE_DAY = 86400000;

    public static void main(String[] args) {
        SpringApplication.run(SocioDbBatchApp.class, args);
    }

    @Scheduled(fixedRate = 5000) // time a a day
    public void run() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("SocioDbBatchId", String.valueOf(System.currentTimeMillis()))
                .addDate("date", new Date())
                .addLong("time", System.currentTimeMillis()).toJobParameters();

        JobExecution execution = jobLauncher.run(job, jobParameters);
        log.info("execution.getStatus(): " + execution.getStatus());
    }
}
