package com.parkalot.infrastructure.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class SensorDevice {

  @Id
  int Id;

  int Status;

  @OneToOne
  @JoinColumn(name = "parkingspaceid")
  public ParkingSpace ParkingSpace;
}
