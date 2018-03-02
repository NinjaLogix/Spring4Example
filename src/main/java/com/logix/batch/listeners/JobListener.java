package com.logix.batch.listeners;

import com.logix.batch.models.DocumentDTO;
import org.springframework.batch.core.ItemWriteListener;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobListener implements ItemWriteListener<DocumentDTO>{
    Logger log = Logger.getLogger(getClass());

    @Override
    public void beforeWrite(List<? extends DocumentDTO> items){
        log.info("JobListener -> Before Execution do some stuff");
    }

    @Override
    public void afterWrite(List<? extends DocumentDTO> items){
        log.info("JobListener -> After Execution do some stuff");
    }

    @Override
    public void onWriteError(Exception ex, List<? extends DocumentDTO> items){
        StringBuffer sb = new StringBuffer("OnWriteError!!!");
        log.info(sb.toString());
        throw new RuntimeException(sb.toString());
    }

}
