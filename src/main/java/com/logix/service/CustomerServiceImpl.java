package com.logix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logix.dao.CustomerDAO;
import com.logix.model.Customer;
import com.logix.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	/*
	 * Usually this would be the class that any manipulation would be made
	 * before passing to the controller. Since this is just a simple
	 * customer model webservice it's just here for representation.
	 */
	@Autowired
	private CustomerDAO custDao;
	
	@Override
	public List<Customer> getAll(){
		return custDao.getAll();
	}
	
	@Override
	public Customer getCust(int id) {
		return custDao.getCust(id);
	}
	
	@Override
	public void newCust(Customer cust) {
		custDao.newCust(cust);
	}
	
	@Override
	public void rmvOne(int id) {
		custDao.rmvOne(id);
	}
	
	@Override
	public void updtCustName(int id, String name) {
		custDao.updtCustName(id, name);
	}

}
