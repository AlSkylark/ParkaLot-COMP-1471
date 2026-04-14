package com.parkalot.infrastructure.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "parkingspaces")
public class ParkingSpace extends BaseModel {

  String code;

  @Column(name = "spacetype")
  Integer spacetype;

  @Column(name = "locationfloor")
  String floor;

  @ManyToOne
  @JoinColumn(name = "locationid")
  public Garage garage;

  @OneToOne(mappedBy = "parkingspace", cascade = CascadeType.ALL)
  public SensorDevice sensor;
}
