package com.parkalot.api.contracts.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public record SpaceReservationDetailDto(
  int id,
  String spaceCode,
  String spaceFloor,
  LocalDate datefrom,
  LocalDate dateto,
  LocalTime timefrom,
  LocalTime timeto,
  String carPlate,
  String priceTypeName
) {}
