package com.logix.batch.config;

import com.logix.batch.listeners.JobListener;
import com.logix.batch.models.DocumentDTO;
import com.logix.batch.processors.UserDocProcessor;
import com.logix.batch.repository.DocumentMapper;
import com.logix.batch.tasklets.UserBatchTasklet;
import com.logix.batch.writers.DocumentWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

@Configuration
/*
 * There is a lot of mischief being created by the @EnableBatchProcessing annotation. This annotation creates a lot of
 * beans in the background as well as an in memory database. Each run has to be unique so I added a jobParameter of
 * current time in seconds so that each job run will be unique. There are many ways to do this such as making your job,
 * re-runnable, or adding an incrementer so each job jas a run id attached to a parameter making it unique, but just for
 * simplistic reasons I have just added a parameter of System.currentTimeMillis()
 */
@EnableBatchProcessing
@ComponentScan("com.logix.batch.*")
public class BatchConfig {
    Logger log = Logger.getLogger(getClass());
    private static final String USER_DOCUMENT_SEL = "select document_id, created_at, data, reference from user_document";

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    DataSource dataSource;

    @Autowired
    UserBatchTasklet userBatchTasklet;

    //-------------------------------------------------------------------------------------------------------->Tasklet/s
    /* Tasklets are used as simple pre/post steps to other steps. In this case I'm just logging to show it works, but
     * you can use this to 'omit' bad data from your job and then have the following step select all the good data. */
    @Bean
    public Step checkUserDocTasklet2(){ return stepBuilderFactory.get("checkUserDocTasklet2").tasklet(userBatchTasklet).build(); }

    //--------------------------------------------------------------------------------------------------------->Reader/s
    @Bean
    public JdbcCursorItemReader<DocumentDTO> reader(){
        JdbcCursorItemReader<DocumentDTO> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql(USER_DOCUMENT_SEL);
        /*Custom row mapper class. The class for this is already implemented,
        * but just wanted to show that there are options for the mapper you use here.*/
        //reader.setRowMapper(mapper());
        //-->By using BeanPropertyRowMapper you don't have to specify a custom RowMapper class.
        reader.setRowMapper(new BeanPropertyRowMapper<>(DocumentDTO.class));
        return reader;
    }

    //TODO - setup a reader using FlatFileItemReader<T> -> future branch updates
    //TODO - setup a reader using ItemReader<T> -> future branch updates

    //------------------------------------------------------------------------------------------------------>Processor/s
    /*
    * For the processor you can process from one object to another, ex: ItemProcessor<FirstDTO, SecondDTO>... You
    * would have to change the writer to use the SecondDTO as well as the chuck settings for the effected step, ex:
    * .<FirstDTO, SecondDTO>chunk(batchChunkSize) The chunk setting just sets the commit time. So basically commit
    * every n, or in this case 'batchChunkSize', transactions.
     */
    @Bean
    public ItemProcessor<DocumentDTO, DocumentDTO> processor(){ return new UserDocProcessor(); }

    //------------------------------------------------------------------------------------------------------>RowMapper/s
    @Bean
    public RowMapper<DocumentDTO> mapper() { return new DocumentMapper(); }

    //------------------------------------------------------------------------------------------------------->Listener/s
    @Bean
    public JobListener listener(){ return new JobListener(); }

    //--------------------------------------------------------------------------------------------------------->Writer/s
    @Bean
    public ItemWriter<DocumentDTO> writer(){return new DocumentWriter(); }

    //TODO - setup a writer using JdbcBatchItemWriter<T> -> future branch updates
    //TODO - setup a writer using FlatFileItemWriter<T> -> future branch updates
    //TODO - setup a writer using CompositeItemWriter<T> -> future branch updates

    //TODO - add a validator for incoming job parameters here -> future branch updates
    //----------------------------------------------------------------------------------------------------------->Step/s
    @Bean
    public Step pickDocuments(){
        return stepBuilderFactory.get("pickDocuments")
                .<DocumentDTO, DocumentDTO>chunk(1)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .listener(listener())
                .build();
    }

    //------------------------------------------------------------------------------------------------------------>Job/s
    @Bean
    public Job addEmplDocument(){
        return jobBuilderFactory.get("addEmplDocument")
                .flow(checkUserDocTasklet2())
                .next(pickDocuments())
                .end()
                .build();
    }
}
