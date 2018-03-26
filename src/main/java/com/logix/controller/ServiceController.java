package com.logix.controller;

import com.logix.model.Customer;
import com.logix.service.CustomerService;
import com.logix.data.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

/**
 *
 * @author bboyingt
 * @version ${version}
 * @since 3.1.0
 */
@RestController
public class ServiceController {

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

        return new ResponseEntity<> (headers, HttpStatus.OK);
    }

    /**
     * --------------------------------------------------------------------------------------> READ
     *
     * @return
     */
    @RequestMapping(value="/listAll", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAll(){
        List<Customer> comps = custService.getAllCustomers();

        return new ResponseEntity<>(comps, HttpStatus.OK);
    }

    /**
     *
     * @param name
     * @return
     */
    @RequestMapping(value="/listAllByName/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAllByName(@PathVariable("name") String name){
        List<Customer> comps = custService.getAllCustomers(name);

        return new ResponseEntity<>(comps, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value="/listAllById/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getAllById(@PathVariable("id") int id){
        Customer cust = custService.getCustomer(id);

        return new ResponseEntity<>(cust, HttpStatus.OK);
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

        return new ResponseEntity<> (headers, HttpStatus.OK);
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

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    //--------------------------------------------------------------------------------------->Spring Security End Points
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public ResponseEntity<Void> registerUser(@RequestBody @Valid UserDto userDto, UriComponentsBuilder uriComponentsBuilder){
        HttpHeaders headers = new HttpHeaders();

        //Attempt to validate userDto to kick off valiation process
            //if failure for any reason return HttpStatus.CONFLICT

        //TODO -> redirect to landing page
        headers.setLocation(uriComponentsBuilder.path("/siteLandingPage/{userId}").buildAndExpand(userDto.getFirstName()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
