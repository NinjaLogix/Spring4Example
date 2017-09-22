package com.logix.service;

import com.logix.dao.CustomerDAO;
import com.logix.model.Customer;
import com.logix.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Customer Service Implimentation
 * @author bboyingt
 * @version ${version}
 * @since 1.0.0
 */
@Service
@Transactional //TODO - research what this really does
public class CustomerServiceImpl implements CustomerService{
	private final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

	public CustomerServiceImpl(){
		log.info("CustomerServiceImpl"); //TODO - change this to something like Class.getClass().getName()
	}

	@Autowired
	private CustomerDAO custDAO;

	@Override
	public int createCustomer(Customer cust){
		return custDAO.createCustomer(cust);
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
