package com.parkalot.api.space_reservation;

import com.parkalot.infrastructure.enums.SpaceType;
import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationRequest(
  String fullName,
  String email,
  String address,
  SpaceType spaceTypeId,
  String carPlate,
  Integer selectedCarId,
  LocalDate startDate,
  LocalDate endDate,
  LocalTime startTime,
  LocalTime endTime
) {}
