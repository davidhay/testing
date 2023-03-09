package org.datavault.testing.service;

import java.time.LocalDate;
import java.util.List;
import org.datavault.testing.entity.Customer;
import org.datavault.testing.repo.CustomerRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService {

  private final CustomerRepo repo;

  public CustomerService(CustomerRepo repo) {
    this.repo = repo;
  }

  public List<Customer> findAll() {
    return repo.findAll();
  }
  public Customer createCustomer(String first, String last, LocalDate dob){
    Customer c = new Customer();
    c.setFirst(first);
    c.setLast(last);
    c.setDob(dob);
    Customer saved = repo.save(c);
    return saved;
  }

  public List<Customer> findByFirstName(String first){
    return repo.findByFirst(first);
  }

  public long count() {
    return repo.count();
  }

  public Customer save(Customer c) {
    return repo.save(c);
  }
}
