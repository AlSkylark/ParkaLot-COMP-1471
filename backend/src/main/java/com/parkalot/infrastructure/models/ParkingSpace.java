package com.parkalot.infrastructure.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "parkingspaces")
public class ParkingSpace {

  @Id
  int Id;

  String Code;
  int SpaceType;

  @Column(name = "locationfloor")
  String Floor;

  @ManyToOne
  @JoinColumn(name = "locationid")
  public Garage Garage;
}
