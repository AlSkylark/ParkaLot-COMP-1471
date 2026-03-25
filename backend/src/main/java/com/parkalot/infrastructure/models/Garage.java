package com.parkalot.infrastructure.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "garages")
public class Garage {

  @Id
  public int Id;

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
