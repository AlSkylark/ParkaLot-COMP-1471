package com.parkalot.api.contracts.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record QuoteDto(
  String name,
  String quoteNumber,
  String spaceCode,
  String spaceFloor,
  int spaceType,
  BigDecimal price,
  LocalDate dateFrom,
  LocalDate dateTo,
  LocalTime timeFrom,
  LocalTime timeTo
) {}
