package com.parkalot.infrastructure.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "discounts")
public class Discount extends BaseModel {

  @Column(name = "discounttype")
  Integer DiscountType;

  @Column(name = "percentageamount")
  Integer PercentageAmount;

  @Column(precision = 10, scale = 2, name = "flatamount")
  BigDecimal FlatAmount;
}
