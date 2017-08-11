package com.logix.service;

import java.util.List;

import com.logix.model.Customer;

/**
 * Customer service definition. This interface defines the entry point to the service layer.
 *
 * @Author Branden Boyington
 * @version
 * @since 1.0.0
 */
public interface CustomerService {
	List<Customer> getAll();
	
	Customer getCust(int id);
	
	void newCust(Customer cust);
	
	void rmvOne(int id);
	
	void updtCustName(int id, String name);
}
