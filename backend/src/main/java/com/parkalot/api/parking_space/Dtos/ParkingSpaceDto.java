package com.parkalot.api.parking_space.Dtos;

import com.parkalot.infrastructure.enums.SensorStatus;
import com.parkalot.infrastructure.enums.SpaceType;

public record ParkingSpaceDto(
  int id,
  String code,
  String floor,
  SpaceType type,
  SensorStatus status
) {}
