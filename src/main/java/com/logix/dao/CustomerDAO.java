package com.logix.dao;

import java.util.List;

import com.logix.model.Customer;

/**
 * Created by ninjalogix on 3/12/17.
 */
public interface CustomerDAO {
	public int createCustomer(Customer cust);
	public Customer updateCustomer(Customer cust);
	public void deleteCustomer(int id);
	public List<Customer> getAllCustomers();
	public Customer getCustomer(int id);
	public List<Customer> getAllCustomers(String customerName);
}
