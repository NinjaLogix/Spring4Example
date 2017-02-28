package com.logix.service;

import java.util.List;

import com.logix.model.Customer;

public interface CustomerService {
	List<Customer> getAll();
	
	Customer getCust(int id);
	
	void newCust(Customer cust);
	
	void rmvOne(int id);
	
	void updtCustName(int id, String name);
}
