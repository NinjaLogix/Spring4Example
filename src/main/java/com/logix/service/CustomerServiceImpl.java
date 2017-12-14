package com.logix.service;

import com.logix.dao.CustomerDAO;
import com.logix.model.Customer;
import com.logix.dto.CustomerDto;
import com.logix.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import java.lang.StringBuilder;

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
	public List<CustomerDto> getAllCustomers(){
		List<Customer> custList = custDAO.getAllCustomers();
		List<CustomerDto> cDto = new ArrayList<>();

		for (Customer c : custList){
			cDto.add(dtoMapper(c));
		}

		return cDto;
	}

	@Override
	public CustomerDto getCustomer(int id){
		return dtoMapper(custDAO.getCustomer(id));
	}

	@Override
	public List<CustomerDto> getAllCustomers(String custName){
		List<Customer> custList = custDAO.getAllCustomers(custName);
		List<CustomerDto> cDto = new ArrayList<>();

		for (Customer c : custList){
			cDto.add(dtoMapper(c));
		}

		return cDto;
	}

	private CustomerDto dtoMapper(Customer tempCust){
		CustomerDto custdto = new CustomerDto();
		custdto.setFullName(tempCust.getCustname());
		custdto.setCity(tempCust.getCity());
		custdto.setId(tempCust.getCustid());

		return custdto;
	}
}
