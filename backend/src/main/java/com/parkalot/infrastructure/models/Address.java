package com.parkalot.infrastructure.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "addresses")
public class Address extends BaseModel {

  private String address1;
  private String address2;
  private String postcode;
  private String town;
  private String country;

  @Override
  public String toString() {
    return (
      this.address1 +
      ", " +
      AddPart(this.address2) +
      AddPart(this.postcode) +
      AddPart(this.town) +
      this.country
    );
  }

  private String AddPart(String part) {
    if (part == null || part.isBlank() || part.isEmpty()) return "";
    return part + ", ";
  }
}
