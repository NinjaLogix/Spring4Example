package com.logix.service;

import java.util.List;

import com.logix.model.Customer;

/**
 * Customer Service interface
 * @author bboyingt
 * @version ${version}
 */
public interface CustomerService {
	public int createCustomer(Customer cust);
	public Customer updateCustomer(Customer cust);
	public void deleteCustomer(int id);
	public List<Customer> getAllCustomers();
	public Customer getCustomer(int id);
	public List<Customer> getAllCustomers(String customerName);
}
