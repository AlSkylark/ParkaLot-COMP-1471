package com.parkalot.api.customer;

import com.parkalot.api.car.dtos.CarCreateRequest;
import com.parkalot.api.car.dtos.CarDto;
import com.parkalot.infrastructure.security.CustomerAuthService;
import com.parkalot.infrastructure.security.JwtUtil;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

record SignInRequest(String email) {}

@RestController
public class CustomerController {

  private final CustomerAuthService auth;
  private final JwtUtil jwt;
  private final CustomerService service;

  public CustomerController(
    CustomerAuthService auth,
    JwtUtil jwt,
    CustomerService service
  ) {
    this.auth = auth;
    this.jwt = jwt;
    this.service = service;
  }

  @GetMapping("/profile")
  public ResponseEntity<?> getMethodName() {
    var userAuth = SecurityContextHolder.getContext().getAuthentication();
    if (userAuth == null || !userAuth.isAuthenticated()) {
      return ResponseEntity.status(401).build();
    }

    var customer = service.GetCustomer(userAuth.getName());
    return ResponseEntity.ok(customer);
  }

  @PostMapping("/signin")
  public ResponseEntity<?> signin(@RequestBody SignInRequest req) {
    try {
      auth.loadUserByUsername(req.email());
    } catch (UsernameNotFoundException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
        Map.of("error", e.getMessage())
      );
    }

    return ResponseEntity.ok(Map.of("token", jwt.generate(req.email())));
  }

  @GetMapping("/customers/{id}/cars")
  public ResponseEntity<List<CarDto>> getCars(@PathVariable int id) {
    var result = service.getCars(id);
    return ResponseEntity.ok(result);
  }

  @PostMapping("/customers/{id}/cars")
  public ResponseEntity<CarDto> addCar(
    @PathVariable int id,
    @RequestBody CarCreateRequest request
  ) {
    var result = service.addCar(id, request);

    return ResponseEntity.ok(result);
  }
}
