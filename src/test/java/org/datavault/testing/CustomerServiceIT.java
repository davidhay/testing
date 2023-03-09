package org.datavault.testing;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;
import org.datavault.testing.entity.Customer;
import org.datavault.testing.app.TestingApplication;
import org.datavault.testing.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(classes = TestingApplication.class)
@Testcontainers(disabledWithoutDocker = true)
@TestPropertySource(properties = {
    "spring.jpa.database-platform=org.hibernate.dialect.MariaDB103Dialect"
})
public class CustomerServiceIT {

  @Container
  static MariaDBContainer<?> container = new MariaDBContainer<>();

  @Autowired
  CustomerService service;

  @Test
  void testExistingCustomers() {
    List<Customer> customers = service.findAll();
    assertEquals(2, customers.size());
  }

  @Test
  void testInsert() {
    long before = service.count();

    Customer c = new Customer();
    c.setFirst("test-first");
    c.setLast("test-last");
    c.setDob(LocalDate.now().minusYears(18));

    Customer saved = service.save(c);
    long after = service.count();
    assertEquals(before + 1, after);

    Customer found = service.findByFirstName("test-first").get(0);
    assertEquals(found, saved);
  }

  @DynamicPropertySource
  static void registerMariaDBProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url",
        () -> container.getJdbcUrl());
    registry.add("spring.datasource.username", () ->  container.getUsername());
    registry.add("spring.datasource.password", () -> container.getPassword());
  }

}
