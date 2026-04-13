package com.parkalot.api.space_reservation.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public record SpaceReservationDto(
  int id,
  String customerName,
  String spaceCode,
  String spaceFloor,
  LocalDate datefrom,
  LocalDate dateto,
  LocalTime timefrom,
  LocalTime timeto,
  String priceTypeName,
  String garageName
) {}
