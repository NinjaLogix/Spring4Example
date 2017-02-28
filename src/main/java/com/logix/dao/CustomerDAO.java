package com.logix.dao;

import com.logix.model.Customer;

import java.util.List;

public interface CustomerDAO {
	/*
	 * Get a list of all customers
	 */
	List<Customer> getAll();
	
	/*
	 * Get a single customer
	 */
	Customer getCust(int id);
	
	/*
	 * set a new customer and return the new customer object by sql selection
	 */
	void newCust(Customer cust);
	
	/*
	 * remove a customer and return the removd object by showing the customer object
	 */
	void rmvOne(int id);
	
	/*
	 * update customer and 
	 */
	void updtCustName(int id, String name);

}
