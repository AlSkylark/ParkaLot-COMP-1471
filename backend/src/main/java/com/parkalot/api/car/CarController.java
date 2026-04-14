package com.parkalot.api.car;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cars")
public class CarController {

  private final CarService service;

  public CarController(CarService service) {
    this.service = service;
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable int id) {
    if (service.deleteCar(id)) return ResponseEntity.noContent().build();
    return ResponseEntity.badRequest().build();
  }
}
