package com.parkalot.api.customer;

import com.parkalot.api.car.dtos.CarDto;
import java.util.List;

public record CustomerDto(
  String email,
  String name,
  String surname,
  boolean isCorporate,
  String address,
  List<CarDto> cars
) {}
