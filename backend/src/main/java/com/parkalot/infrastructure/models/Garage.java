package com.parkalot.infrastructure.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "garages")
public class Garage extends BaseModel {

  @Column(name = "locationname")
  public String Name;

  @OneToOne
  @JoinColumn(name = "addressid")
  public Address Address;

  @OneToMany(mappedBy = "Garage")
  public Set<Scanner> Scanners = new HashSet<>();

  @OneToMany(mappedBy = "Garage")
  public Set<ParkingSpace> ParkingSpaces = new HashSet<>();

  public String getFormattedAddress() {
    if (Address == null) return "";

    return this.Address.toString();
  }
}
