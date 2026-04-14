package com.parkalot.infrastructure.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "garages")
public class Garage extends BaseModel {

  @Column(name = "locationname")
  public String name;

  @OneToOne
  @JoinColumn(name = "addressid")
  public Address address;

  @OneToMany(mappedBy = "garage", cascade = CascadeType.ALL)
  public Set<Scanner> scanners = new HashSet<>();

  @OneToMany(mappedBy = "garage", cascade = CascadeType.ALL)
  public Set<ParkingSpace> parkingspaces = new HashSet<>();

  public String getFormattedAddress() {
    if (address == null) return "";

    return this.address.toString();
  }
}
