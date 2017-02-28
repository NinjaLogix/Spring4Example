package com.logix.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.logix.model.Customer;

import org.springframework.jdbc.core.RowMapper;

public class CustomerMapper implements RowMapper<Customer> {
	
    public static final String BASE_SQL = "Select custid, custname, city from customer";
    public static final String MAX_ID = "Select max(custid) from customer";
    public static final String SINGLE_CUST = "Select custid, custname, city from customer where custid = ?";
    public static final String SINGLE_INSERT = "Insert into customer (custid,custname,city) values (?,?,?)";
    public static final String RMV_CUST = "delete from customer where custid=?";
    public static final String UPDT_CUST_NAME = "update customer set custname=? where custid=?";
    public static final String UPDT_CUST_CITY = "update customer set city=? where custid=?";
    public static final String EXISTS = "select 1 from customer where custid=? and custname=? and city=?";
    public static final String EXISTSID = "select 1 from customer where custid=?";
    public static final String EXISTSNAME = "select 1 from customer where custname=?";
    public static final String EXISTSCITY = "select 1 from customer where city=?";
   
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Integer custid = rs.getInt("custid");
    	String custname = rs.getString("custname");
    	String city = rs.getString("city");
    	
    	return new Customer(custid, custname, city);
    }

}
