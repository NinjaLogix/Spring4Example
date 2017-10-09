package com.logix.dao;

import com.logix.model.Customer;
import com.logix.utils.HibernateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author bboyingt
 * @version ${version}
 * @since 1.1.0
 */
@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private HibernateUtil hiberUtil;

	@Override
	public void createCustomer(Customer cust){
		hiberUtil.create(cust);
	}

	@Override
	public Customer updateCustomer(Customer cust){
		return hiberUtil.update(cust);
	}

	@Override
	public void deleteCustomer(int id){
		Customer cust = new Customer();
		cust.setCustid(id);
		hiberUtil.delete(cust);
	}

	@Override
	public List<Customer> getAllCustomers(){
		return hiberUtil.fetchAll();
	}

	@Override
	public Customer getCustomer(int id){
		return hiberUtil.fetchById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAllCustomers(String custName){
		List<Customer> custObjs = hiberUtil.fetchByName(custName);

		return custObjs;
	}
}
