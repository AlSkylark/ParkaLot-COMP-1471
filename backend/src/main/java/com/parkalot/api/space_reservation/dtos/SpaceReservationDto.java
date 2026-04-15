package com.parkalot.api.space_reservation.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public record SpaceReservationDto(
  int id,
  int contractid,
  String customerName,
  String spaceCode,
  String spaceFloor,
  LocalDate datefrom,
  LocalDate dateto,
  LocalTime timefrom,
  LocalTime timeto,
  String carPlate,
  String priceTypeName,
  String garageName
) {}
