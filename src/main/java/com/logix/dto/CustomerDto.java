package com.logix.dto;

public class CustomerDto {

    public String fullName;
    public String City;
    public int id;

    public String getFullName(){
        return fullName;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public String getCity(){
        return City;
    }

    public void setCity(String City){
        this.City = City;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    @Override
    public String toString(){
        return "FullName: [ " + this.getFullName() + " ]"
                + " City: [ " + this.getCity() + " ]"
                + " Id: [ " + this.getId() + " ]";
    }
}
