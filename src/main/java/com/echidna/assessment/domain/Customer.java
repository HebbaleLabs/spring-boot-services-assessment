package com.echidna.assessment.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;
  private String fullName;

  public Customer() {
  }

  public Customer(String fullName) {
    this.fullName = fullName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Customer customer = (Customer) o;
    return Objects.equals(fullName, customer.fullName);
  }

  @Override
  public int hashCode() {

    return Objects.hash(fullName);
  }

  @Override
  public String toString() {
    return String.format("Customer[id=%d, fullName='%s']", id, fullName);
  }
}
