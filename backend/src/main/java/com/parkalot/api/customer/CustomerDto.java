package com.parkalot.api.customer;

public record CustomerDto(
  String email,
  String name,
  String surname,
  boolean isCorporate,
  String address
) {}
