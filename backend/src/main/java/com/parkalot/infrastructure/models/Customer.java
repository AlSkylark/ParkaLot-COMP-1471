package com.parkalot.infrastructure.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

  @Id
  public int Id;

  @Column
  public String Email;

  @Column(name = "firstname")
  public String FirstName;

  @Column(name = "lastname")
  public String LastName;

  @Column
  public String Title;

  @Column(name = "iscorporate")
  public boolean IsCorporate;

  @ManyToOne
  @JoinColumn(name = "addressid")
  public Address Address;

  public String getFormattedAddress() {
    if (Address == null) return "";

    return this.Address.toString();
  }
}
