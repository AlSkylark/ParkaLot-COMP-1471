package com.parkalot.api.car;

import com.parkalot.infrastructure.models.Car;
import com.parkalot.infrastructure.models.Customer;
import org.springframework.stereotype.Service;

@Service
public class CarService {

  private final CarRepository repo;

  public CarService(CarRepository repo) {
    this.repo = repo;
  }

  public Car getCar(String plateNo) {
    return repo.findByPlateno(plateNo).orElseThrow();
  }

  public Car createCar(Customer customer, String plateNo, int type) {
    var newCar = new Car();
    newCar.setCustomer(customer);
    newCar.setPlateno(plateNo);
    newCar.setCartype(type);

    repo.save(newCar);

    return newCar;
  }

  public boolean deleteCar(int id) {
    try {
      repo.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
