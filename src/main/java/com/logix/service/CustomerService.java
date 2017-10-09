package com.logix.service;

import java.util.List;

import com.logix.model.Customer;

/**
 * Customer Service interface
 * @author bboyingt
 * @version ${version}
 */
public interface CustomerService {
	void createCustomer(Customer cust);
	Customer updateCustomer(Customer cust);
	void deleteCustomer(int id);
	List<Customer> getAllCustomers();
	Customer getCustomer(int id);
	List<Customer> getAllCustomers(String customerName);
}
