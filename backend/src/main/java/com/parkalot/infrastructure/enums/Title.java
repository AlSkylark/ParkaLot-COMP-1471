package com.parkalot.infrastructure.enums;

public enum Title {
  MR("Mr"),
  MRS("Mrs"),
  MS("Ms"),
  DR("Dr"),
  PROF("Prof"),
  SIR("Sir"),
  LADY("Lady"),
  REV("Rev");

  private final String label;

  Title(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
