package com.logix.dao;

import java.util.List;

import com.logix.model.Customer;

/**
 * Created by ninjalogix on 3/12/17.
 * @author bboyingt
 * @version ${version}
 * @since 1.1.0
 */
public interface CustomerDAO {
	void createCustomer(Customer cust);
	Customer updateCustomer(Customer cust);
	void deleteCustomer(int id);
	List<Customer> getAllCustomers();
	Customer getCustomer(int id);
	List<Customer> getAllCustomers(String customerName);
}
