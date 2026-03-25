package com.parkalot.infrastructure.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "parkingspaces")
public class ParkingSpace extends BaseModel {

  String Code;

  @Column(name = "spacetype")
  Integer SpaceType;

  @Column(name = "locationfloor")
  String Floor;

  @ManyToOne
  @JoinColumn(name = "locationid")
  public Garage Garage;
}
