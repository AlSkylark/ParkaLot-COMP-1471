package com.parkalot.infrastructure.enums;

public enum SensorStatus {
  FREE("Free"),
  OCCUPIED("Occupied"),
  RESERVED("Reserved"),
  UNAVAILABLE("Unavailable");

  private final String label;

  SensorStatus(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
