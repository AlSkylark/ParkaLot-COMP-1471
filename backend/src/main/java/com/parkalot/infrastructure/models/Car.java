package com.parkalot.infrastructure.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {

  @Id
  int Id;

  String PlateNo;
  int CarType;

  @ManyToOne
  @JoinColumn(name = "customerid", nullable = false)
  public Customer Customer;
}
