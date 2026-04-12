package com.parkalot.api.customer;

import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private final CustomerRepository repo;

  public CustomerService(CustomerRepository repo) {
    this.repo = repo;
  }

  public CustomerDto GetCustomer(String email) {
    var user = repo.findByEmail(email);

    var customer = user.orElseThrow();

    var dto = new CustomerDto(
      customer.email,
      customer.firstname,
      customer.lastname,
      customer.iscorporate,
      Optional.ofNullable(customer.address)
        .map(a -> a.toString())
        .orElse("")
    );

    return dto;
  }
}
