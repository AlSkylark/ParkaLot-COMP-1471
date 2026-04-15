package com.parkalot.api.contracts.dtos;

import com.parkalot.api.space_reservation.dtos.SpaceReservationDto;
import java.time.LocalDateTime;
import java.util.List;

public record ContractDetailDto(
  int id,
  String contractNumber,
  LocalDateTime dateCreated,
  LocalDateTime dateAgreed,
  boolean isQuote,
  boolean isRecurrent,
  String customerName,
  GuestData guestData,
  List<SpaceReservationDto> reservations
) {}
