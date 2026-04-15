package com.parkalot.api.customer;

import com.parkalot.api.car.CarService;
import com.parkalot.api.car.dtos.CarCreateRequest;
import com.parkalot.api.car.dtos.CarDto;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private final CustomerRepository repo;
  private final CarService carService;

  public CustomerService(CustomerRepository repo, CarService carService) {
    this.repo = repo;
    this.carService = carService;
  }

  public CustomerDto GetCustomer(String email) {
    var user = repo.findByEmail(email);

    var customer = user.orElseThrow();
    var cars = customer
      .getCars()
      .stream()
      .map(c -> new CarDto(c.getId(), c.getPlateno(), c.getCartype()))
      .toList();

    var dto = new CustomerDto(
      customer.email,
      customer.firstname,
      customer.lastname,
      customer.iscorporate,
      Optional.ofNullable(customer.address)
        .map(a -> a.toString())
        .orElse(""),
      cars
    );

    return dto;
  }

  public List<CarDto> getCars(int id) {
    var customer = repo.findById(id).orElseThrow();

    return customer
      .getCars()
      .stream()
      .map(c -> new CarDto(c.getId(), c.getPlateno(), c.getCartype()))
      .toList();
  }

  public CarDto addCar(int id, CarCreateRequest request) {
    var customer = repo.findById(id).orElseThrow();

    var newCar = carService.createCar(
      customer,
      request.plateNo(),
      request.carType()
    );

    customer.getCars().add(newCar);
    repo.save(customer);

    return new CarDto(newCar.getId(), newCar.getPlateno(), newCar.getCartype());
  }
}
