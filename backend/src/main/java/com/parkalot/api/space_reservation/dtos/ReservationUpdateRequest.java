package com.parkalot.api.space_reservation.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationUpdateRequest(
  LocalDate datefrom,
  LocalDate dateto,
  LocalTime timefrom,
  LocalTime timeto
) {}
