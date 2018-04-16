package com.echidna.assessment.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.echidna.assessment.domain.Customer;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan("com.echidna.assessment.service")
public class CustomerServiceTests {

  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private CustomerService customerService;

  /*@Autowired
  private TestCreditScoreUtility testCreditScoreUtility;*/

  @Test
  public void whenCreate_thenReturnCustomer() {
    // given
    Customer customer = new Customer("Ram", 30, 10000L);
    //int eventGenerated = testCreditScoreUtility.getCOUNTER();

    // when
    customer = customerService.create(customer);
    Customer expectedCustomer = entityManager.persistFlushFind(customer);

    // then
    assertThat(expectedCustomer.getId())
        .isGreaterThan(0);
    assertThat(expectedCustomer.getFullName())
        .isEqualTo(customer.getFullName());
    assertThat(expectedCustomer.getAge())
        .isEqualTo(customer.getAge());
    assertThat(expectedCustomer.getSalary())
        .isEqualTo(customer.getSalary());
    /*assertThat(expectedCustomer.getCreditScore())
        .isEqualTo(300000L);
    assertThat(testCreditScoreUtility.getCOUNTER())
        .isEqualTo(eventGenerated + 1);*/
  }

  @Test
  public void whenGetByName_thenReturnCustomer() {
    // given
    Customer customer = new Customer("Ram", 30, 10000L);
    customer = entityManager.persistFlushFind(customer);

    // when
    List<Customer> actualCustomers = customerService
        .getCustomersMatchingFullName(customer.getFullName());

    // then
    assertThat(actualCustomers.get(0).getFullName())
        .isEqualTo(customer.getFullName());
  }

  @Test
  public void whenGetAll_thenReturnCustomers() {
    // given
    Customer customer1 = new Customer("Sita", 25, 5000L);
    customer1 = entityManager.persistFlushFind(customer1);
    Customer customer2 = new Customer("Shyam", 35, 5000L);
    customer2 = entityManager.persistFlushFind(customer2);

    // when
    List<Customer> actualCustomers = customerService.getAll();

    // then
    assertThat(actualCustomers.size())
        .isEqualTo(2);
    assertThat(actualCustomers.get(0).getFullName())
        .isEqualTo(customer1.getFullName());
    assertThat(actualCustomers.get(1).getFullName())
        .isEqualTo(customer2.getFullName());
  }

  @Test
  public void whenGetCustomerById_thenReturnCustomer() {
    // given
    Customer customer = new Customer("Alex", 40, 20000L);
    customer = entityManager.persistFlushFind(customer);

    // when
    Customer actualCustomer = customerService.getCustomer(customer.getId());

    // then
    assertThat(actualCustomer)
        .isNotNull();
    assertThat(actualCustomer.getFullName())
        .isEqualTo(customer.getFullName());
  }

  @Test
  public void whenUpdate_thenReturnCustomer() throws CustomerNotFoundException {
    // given
    Customer customer = new Customer("Ram", 30, 10000L);
    customer = customerService.create(customer);

    // when
    customer.setFullName("Sitaram");
    customer.setSalary(15000L);
    customer = customerService.update(customer.getId(), customer);
    Customer expectedCustomer = entityManager.persistFlushFind(customer);

    // then
    assertThat(expectedCustomer.getId())
        .isGreaterThan(0);
    assertThat(expectedCustomer.getFullName())
        .isEqualTo(customer.getFullName());
    /*assertThat(expectedCustomer.getCreditScore())
        .isEqualTo(450000L);*/
  }

  @Test
  public void whenUpdateNonexistentCustomer_thenFail() throws CustomerNotFoundException {
    // given
    Customer customer = new Customer("Ram", 30, 10000L);
    customer = customerService.create(customer);

    // when
    Long customerId = customer.getId();
    customer.setId(-1L);
    customer.setFullName("Sitaram");

    // then
    exception.expect(CustomerNotFoundException.class);
    customerService.update(customerId, customer);
  }

  @Test
  public void whenDelete_thenPass() throws CustomerNotFoundException {
    // given
    Customer customer = new Customer("Ram", 30, 10000L);
    customer = customerService.create(customer);

    // when
    Long customerId = customer.getId();
    customerService.delete(customerId);
    entityManager.flush();

    // then
    Customer deletedCustomer = customerService.getCustomer(customerId);
    assertThat(deletedCustomer).isNull();
  }

  @Test
  public void whenDeleteNonexistent_thenFail() throws CustomerNotFoundException {
    // given
    Customer customer = new Customer("Ram", 30, 10000L);
    customer = customerService.create(customer);

    // then
    exception.expect(CustomerNotFoundException.class);

    // when
    customerService.delete(-1L);
  }
}
