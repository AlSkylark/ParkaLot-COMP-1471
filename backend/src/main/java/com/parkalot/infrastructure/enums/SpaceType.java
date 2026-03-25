package com.parkalot.infrastructure.enums;

public enum SpaceType {
  NORMAL("Normal"),
  ELECTRIC("Electric");

  private final String label;

  SpaceType(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
