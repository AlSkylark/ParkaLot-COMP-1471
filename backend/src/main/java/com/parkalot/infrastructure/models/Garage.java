package com.parkalot.infrastructure.models;

import jakarta.persistence.*;

@Entity
@Table(name = "garages")
public class Garage {

  @Id
  public int Id;

  @Column(name = "locationname")
  public String Name;

  @ManyToOne
  @JoinColumn(name = "addressid")
  public Address Address;

  public String getFormattedAddress() {
    return this.Address.toString();
  }
}
