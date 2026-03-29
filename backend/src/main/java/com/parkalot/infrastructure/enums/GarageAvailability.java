package com.parkalot.infrastructure.enums;

public enum GarageAvailability {
  HIGH_AVAILABILITY("High availability"),
  MEDIUM_AVAILABILITY("Medium availability"),
  LOW_AVAILABILITY("Low availability"),
  FULL("Full");

  private final String label;

  GarageAvailability(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
