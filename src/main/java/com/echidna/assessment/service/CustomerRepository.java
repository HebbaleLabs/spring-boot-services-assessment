package com.echidna.assessment.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.echidna.assessment.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  List<Customer> findByFullName(String fullName);
}
