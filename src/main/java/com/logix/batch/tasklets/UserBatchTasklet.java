package com.logix.batch.tasklets;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserBatchTasklet implements Tasklet {
    private final Logger log = Logger.getLogger(getClass());

    @Autowired
    private JdbcTemplate template;

    private static final String SELECT_SANITY = "select 1";

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("Running Tasklet.. Here data will be pre checked in user tables for bad documents to prepare for" +
                "expected data...");
        template.execute(SELECT_SANITY);
        return RepeatStatus.FINISHED;
    }

}
