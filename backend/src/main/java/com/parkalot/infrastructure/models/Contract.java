package com.parkalot.infrastructure.models;

import com.parkalot.api.contracts.dtos.GuestData;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "contracts")
public class Contract extends BaseModel {

  @Column(name = "datecreated")
  LocalDateTime dateCreated;

  @Column(name = "dateagreed")
  LocalDateTime dateAgreed;

  @Column(name = "contractnumber")
  String contractNumber;

  @Column(name = "isrecurrent")
  boolean isRecurrent;

  @Column(name = "isquote")
  boolean isQuote;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "tempdata", columnDefinition = "jsonb")
  public GuestData guestData;

  @ManyToOne
  @JoinColumn(name = "customerid", nullable = true)
  private Customer customer;

  @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
  public Set<SpaceReservation> spaceReservations = new HashSet<>();

  @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
  public Set<Invoice> invoices = new HashSet<>();
}
