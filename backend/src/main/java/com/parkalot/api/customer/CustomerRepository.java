package com.parkalot.api.customer;

import com.parkalot.api.BaseRepository;
import com.parkalot.infrastructure.models.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends BaseRepository<Customer> {
  @Query("SELECT c FROM Customer c WHERE c.email = ?1")
  Optional<Customer> findByEmail(String email);
}
