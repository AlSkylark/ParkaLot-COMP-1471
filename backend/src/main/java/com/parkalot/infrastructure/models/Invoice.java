package com.parkalot.infrastructure.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "contracts")
public class Invoice extends BaseModel {

  @Column(name = "datecreated", nullable = false)
  LocalDate datecreated;

  @Column(name = "datepaid")
  LocalDate datepaid;

  @Column(name = "invoicenumber", nullable = false, unique = true)
  String invoicenumber;

  @Column(precision = 10, scale = 2)
  BigDecimal total;

  boolean isrefund;

  @OneToOne
  @JoinColumn(name = "discountid")
  Discount discount;

  @ManyToOne
  @JoinColumn(name = "contractid", nullable = false)
  Contract contract;
}
