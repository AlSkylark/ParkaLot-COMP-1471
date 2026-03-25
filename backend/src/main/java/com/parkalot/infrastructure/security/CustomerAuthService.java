package com.parkalot.infrastructure.security;

import com.parkalot.api.customer.CustomerRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerAuthService implements UserDetailsService {

  private final CustomerRepository userRepo; // your JPA repo

  public CustomerAuthService(CustomerRepository userRepo) {
    this.userRepo = userRepo;
  }

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    // Just check existence — no password needed
    var user = userRepo.findByEmail(username);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException("Customer is not in our records");
    }

    return User.withUsername(username)
      .password("") // blank — we're not checking passwords
      .roles("USER")
      .build();
  }
}
