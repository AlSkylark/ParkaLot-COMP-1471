package com.parkalot.infrastructure.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "sensordevice")
public class SensorDevice extends BaseModel {

  @Column(name = "sensorstatus")
  Integer status;

  @OneToOne
  @JoinColumn(name = "parkingspaceid")
  public ParkingSpace parkingspace;
}
