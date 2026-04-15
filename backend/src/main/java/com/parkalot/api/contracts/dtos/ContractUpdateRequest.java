package com.parkalot.api.contracts.dtos;

public record ContractUpdateRequest(boolean isRecurrent, GuestData guestData) {}
