package com.logix.service;

import com.logix.data.CustomerDAO;
import com.logix.model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Customer Service Implimentation
 * @author bboyingt
 * @version ${version}
 * @since 1.1.0
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDAO custDAO;

	@Override
	public void createCustomer(Customer cust){
		custDAO.createCustomer(cust);
	}

	@Override
	public Customer updateCustomer(Customer cust){
		return custDAO.updateCustomer(cust);
	}

	@Override
	public void deleteCustomer(int id){
		custDAO.deleteCustomer(id);
	}

	@Override
	public List<Customer> getAllCustomers(){
		return custDAO.getAllCustomers();
	}

	@Override
	public Customer getCustomer(int id){
		return custDAO.getCustomer(id);
	}

	@Override
	public List<Customer> getAllCustomers(String custName){
		return custDAO.getAllCustomers(custName);
	}
}
