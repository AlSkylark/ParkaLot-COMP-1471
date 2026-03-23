package com.parkalot.infrastructure.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "pricetypes")
public class PriceType {

  @Id
  int Id;

  String Name;

  @Column(precision = 10, scale = 2)
  BigDecimal Price;
}
