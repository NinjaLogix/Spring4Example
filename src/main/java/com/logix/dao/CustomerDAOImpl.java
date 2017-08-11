package com.logix.dao;

import java.util.List;

import javax.sql.DataSource;

import com.logix.controller.ComponentController;
import com.logix.mapper.CustomerMapper;
import com.logix.model.Customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;

/**
 * Customer Data Access Object implimentation. This class is the repository for the customer data access layer intry interface.
 * This class is also marked as transactional to control database interaction.
 *
 * @author Branden Boyington
 * @version ${version}
 * @since 1.0.0
 */
@Repository //generic annotation to mark as a repository for a service
@Transactional
public class CustomerDAOImpl extends JdbcDaoSupport implements CustomerDAO {
	
	private final Logger Log = LoggerFactory.getLogger(CustomerDAOImpl.class);
	
	@Autowired
	public CustomerDAOImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	public int getNewCustId() {
		String sql = CustomerMapper.MAX_ID;
		
        Integer value = this.getJdbcTemplate().queryForObject(sql, Integer.class);
        Log.info("CustomerDAOImpl --> value: " + value.toString());
        
        if (value == null)
            return 0;
        else
        	return value += 1;
	}
	
	public int exists(Customer cust){
		String sql = CustomerMapper.EXISTS;
		
		Object[] params = new Object[] { cust.getId(), cust.getName(), cust.getCity() };
		try {
			Integer temp = this.getJdbcTemplate().queryForObject(sql, params, Integer.class);
			Log.info("Customer is already created: {}", cust);
			return temp;
		}catch (EmptyResultDataAccessException e) {//return 0 when sql is empty
			Log.info("Customer is avaliable to be created");
			Log.info("Expeption: {}", e);
			return 0;
		}
	}
	
	public int existsId(int id) {
		String sql = CustomerMapper.EXISTSID;
		
		Object[] params = new Object[] { id };
		try {
			Integer temp = this.getJdbcTemplate().queryForObject(sql, params, Integer.class);
			return temp;
		}catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}
	
	public int existsName(String name) {
		String sql = CustomerMapper.EXISTSNAME;
		
		Object[] params = new Object[] { name };
		try {
			Integer temp = this.getJdbcTemplate().queryForObject(sql, params, Integer.class);
			return temp;
		}catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}
	
	public int existsCity(String city) {
		String sql = CustomerMapper.EXISTSCITY;
		
		Object[] params = new Object[] { city };
		try {
			Integer temp = this.getJdbcTemplate().queryForObject(sql, params, Integer.class);
			return temp;
		}catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}
	
	@Override
	public List<Customer> getAll(){
		String sql = CustomerMapper.BASE_SQL;
		
		Object[] parms = new Object[] {};
		CustomerMapper mapper = new CustomerMapper();
		
		List<Customer> list = this.getJdbcTemplate().query(sql, parms, mapper);
		return list;
	}
	
	@Override
	public Customer getCust(int id) {
		String sql = CustomerMapper.SINGLE_CUST;
		Object[] params = new Object[] { id };
		CustomerMapper mapper = new CustomerMapper();
		
		int temp = this.existsId(id);
		Customer cust = new Customer();
		
		if (temp == 1)
			cust = this.getJdbcTemplate().queryForObject(sql, params, mapper);
		else
			Log.info("Customer was not found with id {}", id);
		
		return cust;
		
	}
	
	@Override
	public void newCust(Customer cust) {
		String sql = CustomerMapper.SINGLE_INSERT;
		cust.setId(this.getNewCustId());
		
		int exName = this.existsName(cust.getName());
		int exCity = this.existsCity(cust.getCity());
		
		if (exName == 1 && exCity ==1) {
			Log.info("Can't create customer. A customer with name {} and city {} already exists", cust.getName(), cust.getCity());
			//TODO -- add exeption here
		}else {
			Object[] params = new Object[] { cust.getId(), cust.getName(), cust.getCity()};
			this.getJdbcTemplate().update(sql, params);			
		}
	}
	
	@Override
	public void rmvOne(int id) {
		String sql = CustomerMapper.RMV_CUST;
		
		int exId = this.existsId(id);
		
		if (exId == 1) {
			Object[] params = new Object[] { id };
	        this.getJdbcTemplate().update(sql, params);
		}else
			Log.info("Customer with id {} don't exists", id);
	}
	
	@Override
	public void updtCustName(int id, String name) {
		String sql = CustomerMapper.UPDT_CUST_NAME;
		try {
			Customer upCust = this.getCust(id);
			upCust.setName(name);
			
			Object[] params = new Object[] { upCust.custname, id };
			this.getJdbcTemplate().update(sql, params);
		}catch (EmptyResultDataAccessException e) {
			Log.info("Failed to update for customer ");
		}
	}

}
