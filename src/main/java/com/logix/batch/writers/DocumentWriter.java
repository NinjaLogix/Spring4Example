package com.logix.batch.writers;

import com.logix.batch.models.DocumentDTO;
import org.springframework.batch.item.ItemWriter;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class DocumentWriter implements ItemWriter<DocumentDTO> {
    Logger log = Logger.getLogger(getClass());

    @Override
    public void write(List<? extends DocumentDTO> documents) throws Exception{
        for (DocumentDTO document: documents){
            log.info("DocumentWriter --> " + document);
        }
    }
}
