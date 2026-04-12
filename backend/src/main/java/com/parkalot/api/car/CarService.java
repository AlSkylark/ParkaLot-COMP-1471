package com.parkalot.api.car;

import com.parkalot.infrastructure.models.Car;
import org.springframework.stereotype.Service;

@Service
public class CarService {

  private final CarRepository repo;

  public CarService(CarRepository repo) {
    this.repo = repo;
  }

  public Car getOrCreateCar(String plateNo) {
    var existing = repo.findByPlateno(plateNo);

    return existing.orElseGet(() -> {
      var newCar = new Car();
      newCar.setPlateno(plateNo);
      return newCar;
    });
  }
}
