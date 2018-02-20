package com.echidna.assessment.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.echidna.assessment.domain.Customer;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan("com.echidna.assessment.service")
public class CustomerServiceTests {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private CustomerService customerService;

  @Test
  public void whenCreate_thenReturnCustomer() {
    // given
    Customer customer = new Customer("Ram");

    // when
    customer = customerService.create(customer);
    Customer expectedCustomer = entityManager.persistFlushFind(customer);

    // then
    assertThat(expectedCustomer.getId())
        .isGreaterThan(0);
    assertThat(expectedCustomer.getFullName())
        .isEqualTo(customer.getFullName());
  }

  @Test
  public void whenGetByName_thenReturnCustomer() {
    // given
    Customer customer = new Customer("Ram");
    customer = entityManager.persistFlushFind(customer);

    // when
    List<Customer> actualCustomers = customerService.getCustomersMatchingFullName(customer.getFullName());

    // then
    assertThat(actualCustomers.get(0).getFullName())
        .isEqualTo(customer.getFullName());
  }

  @Test
  public void whenGetAll_thenReturnCustomers() {
    // given
    Customer customer1 = new Customer("Sita");
    customer1 = entityManager.persistFlushFind(customer1);
    Customer customer2 = new Customer("Sita");
    customer2 = entityManager.persistFlushFind(customer2);

    // when
    List<Customer> actualCustomers = customerService.getAll();

    // then
    assertThat(actualCustomers.size())
        .isEqualTo(2);
    assertThat(actualCustomers.get(0).getFullName())
        .isEqualTo(customer1.getFullName());
    assertThat(actualCustomers.get(0).getFullName())
        .isEqualTo(customer2.getFullName());
  }

  @Test
  public void whenGetCustomerById_thenReturnCustomer() {
    // given
    Customer customer = new Customer("Alex");
    customer = entityManager.persistFlushFind(customer);

    // when
    Customer actualCustomer = customerService.getCustomer(customer.getId());

    // then
    assertThat(actualCustomer)
        .isNotNull();
    assertThat(actualCustomer.getFullName())
        .isEqualTo(customer.getFullName());
  }
}
