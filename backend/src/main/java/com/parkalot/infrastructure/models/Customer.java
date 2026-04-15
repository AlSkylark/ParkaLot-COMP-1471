package com.parkalot.infrastructure.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "customers")
public class Customer extends BaseModel {

  @Column
  public String email;

  @Column(name = "firstname")
  public String firstname;

  @Column(name = "lastname")
  public String lastname;

  @Column
  public Integer title;

  @Column(name = "iscorporate")
  public boolean iscorporate;

  @OneToOne
  @JoinColumn(name = "addressid")
  public Address address;

  @OneToMany(mappedBy = "customer")
  public Set<Car> cars = new HashSet<>();

  public String getFormattedAddress() {
    if (address == null) return "";

    return this.address.toString();
  }

  public String getFullName() {
    return this.firstname + " " + this.lastname;
  }
}
