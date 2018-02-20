package com.echidna.assessment.service;

import java.util.List;

import com.echidna.assessment.domain.Customer;

public interface CustomerService {

  Customer create(Customer customer);

  Customer getCustomer(Long customerId);

  List<Customer> getAll();

  List<Customer> getCustomersMatchingFullName(String fullName);

}
