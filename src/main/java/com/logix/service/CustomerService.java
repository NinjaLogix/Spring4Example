package com.logix.service;

import java.util.List;

import com.logix.model.Customer;
import com.logix.dto.CustomerDto;

/**
 * Customer Service interface
 * @author bboyingt
 * @version ${version}
 */
public interface CustomerService {
	void createCustomer(Customer cust);
	Customer updateCustomer(Customer cust);
	void deleteCustomer(int id);
	List<CustomerDto> getAllCustomers();
	CustomerDto getCustomer(int id);
	List<CustomerDto> getAllCustomers(String customerName);
}
