package com.echidna.assessment.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;
  private String fullName;
  private Integer age;
  private Long salary;

  public Customer() {
  }

  public Customer(String fullName, Integer age, Long salary) {
    this.fullName = fullName;
    this.age = age;
    this.salary = salary;
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

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Long getSalary() {
    return salary;
  }

  public void setSalary(Long salary) {
    this.salary = salary;
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
