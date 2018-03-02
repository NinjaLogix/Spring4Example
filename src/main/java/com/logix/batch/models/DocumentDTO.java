package com.logix.batch.models;

public class DocumentDTO {
    private int document_id, reference;
    private String data, created_at;

    public int getDocument_id(){ return document_id; }
    public void setDocument_id(int document_id){
        this.document_id = document_id;
    }

    public int getReference(){ return reference; }
    public void setReference(int reference){
        this.reference = reference;
    }

    public String getData(){ return data; }
    public void setData(String data){
        this.data = data;
    }

    public String getCreated_at(){ return created_at; }
    public void setCreated_at(String created_at){
        this.created_at = created_at;
    }

    @Override
    public String toString(){
        return "UserDocumentPOJO [document_id -> " + this.document_id
                + " reference -> " + this.reference + " data -> " + this.data
                + " created_at - > " + this.created_at + "]";
    }
}
