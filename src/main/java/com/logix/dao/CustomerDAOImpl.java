package com.logix.dao;

import com.logix.dao.CustomerDAO;
import com.logix.model.Customer;
import com.logix.utils.HibernateUtil;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bboyingt
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public class CustomerDAOImpl implements CustomerDAO {
	private final Logger log = LoggerFactory.getLogger(CustomerDAOImpl.class);
	public CustomerDAOImpl(){
		log.info("CustomerDAOImpl");
	}

	@Autowired
	private HibernateUtil hiberUtil;

	@Override
	public int createCustomer(Customer cust){
		return (int)  hiberUtil.create(cust);
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
		return hiberUtil.fetchAll(Customer.class);
	}

	@Override
	public Customer getCustomer(int id){
		return hiberUtil.fetchById(id, Customer.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAllCustomers(String custName){
		//String query = "SELECT * FROM customer e WHERE e.name like '%" + custName + "%'";
		String query = "from Customer c where c.name like '%" + custName + "%'"; //TODO - fix sql grammar from example
		List<Object[]> custObjs = hiberUtil.fetchAll(query);
		List<Customer> custs = new ArrayList<>();
		for(Object[] custObj: custObjs){
			Customer cust = new Customer();
			cust.setCustid((int) custObj[0]);
			cust.setCustname((String) custObj[1]);
			cust.setCity((String) custObj[2]);
		}

		log.info(custs.toString());
		return custs;
		//return hiberUtil.fetchAll(custName);
	}
}
