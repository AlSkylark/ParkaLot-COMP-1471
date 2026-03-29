package com.parkalot.api.parking_space.Dtos;

public class ParkingAvailabilityDto {

  public final int total;
  public final int free;

  public ParkingAvailabilityDto(Long total, Long free) {
    this.total = total.intValue();
    this.free = free.intValue();
  }
}
