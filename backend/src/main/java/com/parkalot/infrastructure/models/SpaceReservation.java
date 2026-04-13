package com.parkalot.infrastructure.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "spacereservations")
public class SpaceReservation extends BaseModel {

  @Column(name = "datefrom")
  private LocalDate datefrom;

  @Column(name = "dateto")
  private LocalDate dateto;

  @Column(name = "timefrom")
  private LocalTime timefrom;

  @Column(name = "timeto")
  private LocalTime timeto;

  @OneToOne
  @JoinColumn(name = "carid", nullable = true)
  public Car car;

  @OneToOne
  @JoinColumn(name = "spaceid", nullable = false)
  public ParkingSpace space;

  @OneToOne
  @JoinColumn(name = "pricetypeid", nullable = false)
  private PriceType pricetype;

  @ManyToOne
  @JoinColumn(name = "contractid", nullable = false)
  private Contract contract;
}
