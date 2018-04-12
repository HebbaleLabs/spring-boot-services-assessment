package com.echidna.assessment.service;

import com.echidna.assessment.domain.Customer;

public class CreditScoreRequestEvent {

  private final Customer customer;

  public CreditScoreRequestEvent(Customer customer) {
    this.customer = customer;
  }

  public Customer getCustomer() {
    return customer;
  }
}
