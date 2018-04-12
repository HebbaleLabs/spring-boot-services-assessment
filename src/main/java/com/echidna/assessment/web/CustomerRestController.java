package com.echidna.assessment.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.echidna.assessment.domain.Customer;
import com.echidna.assessment.service.CustomerService;
import com.echidna.assessment.service.CustomerNotFoundException;

@RestController
@EnableAutoConfiguration
public class CustomerRestController {

  @Autowired
  private CustomerService customerService;

  @RequestMapping("/customers")
  List<Customer> listAllCustomers(@RequestParam(value="name", required = false) String matchesName) {
    if (matchesName != null && matchesName.length() > 0) {
      return customerService.getCustomersMatchingFullName(matchesName);
    } else {
      return customerService.getAll();
    }
  }

  @RequestMapping("/customers/{customerId}")
  ResponseEntity<Customer> getSingleCustomer(@PathVariable Long customerId) {
    Customer customer = customerService.getCustomer(customerId);
    if(customer == null) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(customer, HttpStatus.OK);
    }
  }

  @RequestMapping(value = "/customers", method = RequestMethod.POST)
  ResponseEntity<?> create(@RequestBody Customer customer) {
    customer = customerService.create(customer);
    return new ResponseEntity<>(customer, HttpStatus.OK);
  }

  @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.PUT)
  ResponseEntity<?> update(@PathVariable Long customerId, @RequestBody Customer customer) {
    try {
      customer = customerService.update(customerId, customer);
      return new ResponseEntity<>(customer, HttpStatus.OK);
    } catch (CustomerNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.DELETE)
  ResponseEntity<?> delete(@PathVariable Long customerId) {
    try {
      customerService.delete(customerId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (CustomerNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
