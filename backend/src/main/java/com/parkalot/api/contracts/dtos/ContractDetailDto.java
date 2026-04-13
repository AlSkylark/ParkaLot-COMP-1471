package com.parkalot.api.contracts.dtos;

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
  List<SpaceReservationDetailDto> reservations
) {}
