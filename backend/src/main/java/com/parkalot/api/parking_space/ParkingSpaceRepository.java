package com.parkalot.api.parking_space;

import com.parkalot.infrastructure.models.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSpaceRepository
  extends JpaRepository<ParkingSpace, Integer> {}
