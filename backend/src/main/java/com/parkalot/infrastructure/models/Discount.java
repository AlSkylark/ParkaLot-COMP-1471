package com.parkalot.infrastructure.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "discounts")
public class Discount {

  @Id
  int Id;

  int DiscountType;
  int PercentageAmount;

  @Column(precision = 10, scale = 2)
  BigDecimal FlatAmount;
}
