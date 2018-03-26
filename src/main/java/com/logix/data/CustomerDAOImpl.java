package com.logix.data;

import com.logix.model.Customer;
import com.logix.utils.DataUtil;

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
	private DataUtil hiberUtil;

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
