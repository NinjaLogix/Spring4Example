package com.logix.batch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.apache.log4j.Logger;
import java.util.Date;

@ComponentScan("com.logix.batch.*")
@EnableAutoConfiguration
public class Application {
    private static Logger log = Logger.getLogger(Application.class);

    private static final String ADD_DOCUMENT_JOB = "addEmplDocument";

    public static void main(String[] args) throws BeansException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException, InterruptedException {
        log.info("Logger enabled: Entering main \n\n");

        /* Launching all jobs automatically by application context.
         * You will have to specify job parameters from command line. */
        //ApplicationContext ctx = SpringApplication.run(Application.class, args);

        //Launching manually with parameters
        SpringApplication app = new SpringApplication(Application.class);
        app.setWebEnvironment(false);
        ConfigurableApplicationContext ctx = app.run(ADD_DOCUMENT_JOB);
        JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("startDate", new Date())
                .addLong("startTime", System.currentTimeMillis()) //This makes each run unique so it can be ran
                .toJobParameters();
        Job emplDocumentJob = ctx.getBean("addEmplDocument", Job.class);
        JobExecution jobExecution = jobLauncher.run(emplDocumentJob, jobParameters);

        if (jobExecution.getStatus().equals(BatchStatus.COMPLETED))
            log.info("Success!!!");
        else
            log.error("Job failed!!! Something went wrong!!!!");
    }
}
