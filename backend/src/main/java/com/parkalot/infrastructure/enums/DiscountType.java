package com.parkalot.infrastructure.enums;

public enum DiscountType {
  ANNIVERSARY("Anniversary"),
  CORPORATE_BULK("Corporate Bulk"),
  INDIVIDUAL_BULK("Individual Bulk"),
  LOYALTY_5_YEARS("Loyalty 5 Years"),
  LOYALTY_10_YEARS("Loyalty 10 Years"),
  PROMOTIONAL("Promotional");

  private final String label;

  DiscountType(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
