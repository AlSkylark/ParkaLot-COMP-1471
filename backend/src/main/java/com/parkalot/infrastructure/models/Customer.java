package com.parkalot.infrastructure.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;

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

  @OneToOne
  @JoinColumn(name = "addressid")
  public Address Address;

  @OneToMany(mappedBy = "Customer")
  public Set<Car> Cars = new HashSet<>();

  public String getFormattedAddress() {
    if (Address == null) return "";

    return this.Address.toString();
  }
}
