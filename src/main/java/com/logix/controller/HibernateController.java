package com.logix.controller;

import com.logix.model.Customer;
import com.logix.service.CustomerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Not setting a filter on this example, but we will be setting custom http statues on output of controller.
 * Controller setup is mostly the same as the JDBC example.
 *
 * @author bboyingt
 * @version ${version}
 * @since 1.0.0
 */
@RestController
public class HibernateController {
    private final Logger log = LoggerFactory.getLogger(HibernateController.class);

    @Autowired
    private CustomerService custService;

    /**
     * ------------------------------------------------------------------------------------> CREATE
     * @param name
     * @param city
     * @return
     */
    @RequestMapping(value="/create/{name}/{city}/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> createNewCust(@PathVariable("name") String name,
                                              @PathVariable("city") String city,
                                              @PathVariable("id") int id){
        HttpHeaders headers = new HttpHeaders();
        Customer cust = new Customer();
        cust.setCustname(name);
        cust.setCity(city);
        cust.setCustid(id);
        custService.createCustomer(cust);

        return new ResponseEntity<Void> (headers, HttpStatus.OK);
    }

    /**
     * --------------------------------------------------------------------------------------> READ
     * @return
     */
    @RequestMapping(value="/listAll", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAll(){
        log.info("Getting all customers");
        List<Customer> comps = custService.getAllCustomers();

        if (comps == null || comps.isEmpty()){
            log.warn("No customers were found");
        }

        return new ResponseEntity<List<Customer>>(comps, HttpStatus.OK);
    }

    @RequestMapping(value="/listAllByName/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAllByName(@PathVariable("name") String name){
        List<Customer> comps = custService.getAllCustomers(name);
        if (comps == null || comps.isEmpty()){
            log.warn("No customer of: " + name + " was found...");
        }

        return new ResponseEntity<List<Customer>>(comps, HttpStatus.OK);
    }

    /**
     * ------------------------------------------------------------------------------------> UPDATE
     * @param id
     * @param name
     * @return
     */
    @RequestMapping(value="/update/{id}/{name}/{city}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateCust(@PathVariable("id") int id,
                                           @PathVariable("name") String name,
                                           @PathVariable("city") String city){
        HttpHeaders headers = new HttpHeaders();
        Customer cust = new Customer();
        cust.setCustid(id);
        cust.setCustname(name);
        cust.setCity(city);
        custService.updateCustomer(cust);

        return new ResponseEntity<Void> (headers, HttpStatus.OK);
    }

    /**
     * ------------------------------------------------------------------------------------> DELETE
     * @param id
     * @return
     */
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> remove(@PathVariable("id") int id){
        HttpHeaders headers = new HttpHeaders();
        custService.deleteCustomer(id);
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

}
