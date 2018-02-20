package com.echidna.assessment.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.echidna.assessment.domain.Customer;

@Component
public class CustomerServiceImpl implements CustomerService {

  @Override
  public Customer create(Customer customer) {
    return null;
  }

  @Override
  public Customer getCustomer(Long customerId) {
    return null;
  }

  @Override
  public List<Customer> getAll() {
    return null;
  }

  @Override
  public List<Customer> getCustomersMatchingFullName(String fullName) {
    return null;
  }
}
