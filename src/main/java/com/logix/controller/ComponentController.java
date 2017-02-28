package com.logix.controller;

import com.logix.model.Customer;
import com.logix.service.CustomerService;
import com.mysql.jdbc.log.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

@RestController
public class ComponentController {
	
	private final Logger Log = LoggerFactory.getLogger(ComponentController.class);
	
	@Autowired
	private CustomerService custMed;
	
	//===================================================================Add new component ~ CREATE
	@RequestMapping(value="/create/{name}/{city}", method = RequestMethod.POST)
	public ResponseEntity<Void> createFromPath(@PathVariable("name") String name, @PathVariable("city") String city){
		
        HttpHeaders headers = new HttpHeaders();
		Customer cust = new Customer();
		cust.setName(name);
		cust.setCity(city);
		custMed.newCust(cust);
		
		return new ResponseEntity<Void> (headers, HttpStatus.OK);
	}
	
	//=========================================================================Get All Users ~ READ
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> getAll(){
		Log.info("getting all users");
		List<Customer> comps = custMed.getAll();

		if (comps == null || comps.isEmpty())
			Log.info("no components found");
		
		return new ResponseEntity<List<Customer>>(comps, HttpStatus.OK);
	}
	
	//=================================================================Get only one customer ~ READ
	@RequestMapping(value="/byId/{id}", method = RequestMethod.GET)
	public ResponseEntity<Customer> get(@PathVariable("id") int id){
		Log.info("getting user by id");

		Customer cust = custMed.getCust(id);
		Log.info("Info: {}", cust);
		
        if (cust == null){
            Log.info("user with id {} not found", id);
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
		
		return new ResponseEntity<Customer>(cust, HttpStatus.OK);
	}
	
	//===========================================================Update existing component ~ UPDATE
	@RequestMapping(value="/update/{id}/{name}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateName(@PathVariable("id") int id, @PathVariable("name") String name){
		HttpHeaders headers = new HttpHeaders();
		
		custMed.updtCustName(id, name);
		
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
	
	//================================================================Remove new component ~ DELETE
	@RequestMapping(value="/remove/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> remove(@PathVariable("id") int id){
		HttpHeaders headers = new HttpHeaders();
		
		Log.info("Attempting to delete user with id {}", id);
		
		custMed.rmvOne(id); 
		
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

}
