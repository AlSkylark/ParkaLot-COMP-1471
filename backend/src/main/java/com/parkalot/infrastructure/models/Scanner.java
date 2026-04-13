package com.parkalot.infrastructure.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class Scanner extends BaseModel {

  @Column(name = "scannertype")
  Integer scannertype;

  @ManyToOne
  @JoinColumn(name = "locationid", nullable = false)
  private Garage garage;
}
