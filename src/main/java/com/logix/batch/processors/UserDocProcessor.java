package com.logix.batch.processors;

import com.logix.batch.models.DocumentDTO;
import org.springframework.batch.item.ItemProcessor;
import org.apache.log4j.Logger;

public class UserDocProcessor implements ItemProcessor<DocumentDTO, DocumentDTO> {
    private static final Logger log = Logger.getLogger(UserDocProcessor.class);

    @Override
    public DocumentDTO process(DocumentDTO documentDTO) throws Exception {
        DocumentDTO documentDTOChanged = new DocumentDTO();

        documentDTOChanged.setCreated_at(documentDTO.getCreated_at());
        documentDTOChanged.setData(documentDTO.getData());
        documentDTOChanged.setDocument_id(documentDTO.getDocument_id());
        documentDTOChanged.setReference(documentDTO.getReference());
        log.info("DocumentProcessor -> current document: " + documentDTOChanged.toString());

        return documentDTOChanged;
    }
}
