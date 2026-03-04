package com.parkalot.infrastructure.models;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

  @Id
  int Id;

  String Address1;
  String Address2;
  String Postcode;
  String Town;
  String Country;

  @Override
  public String toString() {
    return (
      this.Address1 +
      ", " +
      AddPart(this.Address2) +
      AddPart(this.Postcode) +
      AddPart(this.Town) +
      this.Country
    );
  }

  private String AddPart(String part) {
    if (part == null || part.isBlank() || part.isEmpty()) return "";
    return part + ", ";
  }
}
