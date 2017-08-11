package com.logix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logix.dao.CustomerDAO;
import com.logix.model.Customer;
import com.logix.service.CustomerService;

/**
 * Customer service implimentation. This class defines the service layer of the application. This layer talks directly
 * with the DAO layer of the application.
 * @author Branden Boyington
 * @version ${version}
 * @since 1.0.0
 */
@Service
public class CustomerServiceImpl implements CustomerService{

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
