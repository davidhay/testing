package org.datavault.testing.repo;

import java.time.LocalDate;
import java.util.List;
import org.datavault.testing.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepo extends CrudRepository<Customer, String> {

  List<Customer> findAll();
  List<Customer> findByFirst(String first);
  List<Customer> findByLast(String last);
  List<Customer> findByDob(LocalDate dob);
}
