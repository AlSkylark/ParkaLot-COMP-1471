package com.parkalot.infrastructure.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "cars")
public class Car extends BaseModel {

  String plateno;
  int cartype;

  @ManyToOne
  @JoinColumn(name = "customerid", nullable = false)
  public Customer customer;
}
