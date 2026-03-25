package com.parkalot.infrastructure.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Scanner {

  @Id
  int Id;

  String ScannerType;

  @ManyToOne
  @JoinColumn(name = "locationid", nullable = false)
  private Garage Garage;
}
