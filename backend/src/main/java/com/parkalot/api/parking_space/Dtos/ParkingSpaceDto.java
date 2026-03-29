package com.parkalot.api.parking_space.Dtos;

import com.parkalot.infrastructure.enums.SpaceType;

public record ParkingSpaceDto(String code, String floor, SpaceType type) {}
