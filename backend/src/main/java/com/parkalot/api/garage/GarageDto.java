package com.parkalot.api.garage;

import com.parkalot.api.parking_space.Dtos.ParkingSpaceDto;
import com.parkalot.infrastructure.enums.GarageAvailability;
import java.util.List;
import java.util.Optional;

public record GarageDto(
  int id,
  String name,
  String address,
  GarageAvailability availability,
  Optional<List<ParkingSpaceDto>> spaces
) {}
