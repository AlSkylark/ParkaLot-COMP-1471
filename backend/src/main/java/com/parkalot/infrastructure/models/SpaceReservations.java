package com.parkalot.infrastructure.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "spacereservations")
@Getter
@Setter
public class SpaceReservations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "contractid")
    private Integer contractId;

    @Column(name = "pricetypeid")
    private Integer priceTypeId;

    @Column(name = "spaceid")
    private Integer spaceId;

    @Column(name = "carid")
    private Integer carId;
}