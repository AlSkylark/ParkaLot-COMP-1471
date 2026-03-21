package com.parkalot.api.customer;

import com.parkalot.infrastructure.models.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
  @Query("SELECT c FROM Customer c WHERE c.Email = ?1")
  Optional<Customer> findByEmail(String email);
}
