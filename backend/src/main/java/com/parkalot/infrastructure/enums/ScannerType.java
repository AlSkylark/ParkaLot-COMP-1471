package com.parkalot.infrastructure.enums;

public enum ScannerType {
  ENTRY("Entry"),
  EXIT("Exit"),
  BOTH("Both");

  private final String label;

  ScannerType(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
