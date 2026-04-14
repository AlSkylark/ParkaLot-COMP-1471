package com.parkalot.api.contracts.dtos;

public record GuestData(
  String fullName,
  String email,
  String address,
  String carPlate
) {}
