package com.logix.batch.repository;

import com.logix.batch.models.DocumentDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DocumentMapper implements RowMapper<DocumentDTO> {

    @Override
    public DocumentDTO mapRow(ResultSet rs, int rowNum) throws SQLException{
        DocumentDTO document = new DocumentDTO();

        document.setDocument_id(rs.getInt("document_id"));
        document.setCreated_at(rs.getString("created_at"));
        document.setData(rs.getString("data"));
        document.setReference(rs.getInt("reference"));

        document.setReference(document.getReference()-2);
        return document;
    }
}
