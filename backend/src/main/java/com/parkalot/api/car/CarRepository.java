package com.parkalot.api.car;

import com.parkalot.api.BaseRepository;
import com.parkalot.infrastructure.models.Car;
import java.util.Optional;

public interface CarRepository extends BaseRepository<Car> {
  Optional<Car> findByPlateno(String plateNo);
}
