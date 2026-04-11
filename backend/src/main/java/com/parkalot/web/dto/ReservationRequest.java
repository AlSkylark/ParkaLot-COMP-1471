package com.parkalot.web.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationRequest {

    public LocalDate date;
    public LocalTime time;

    public int spaceId;
    public int priceTypeId;
    public int contractId;
    public int carId;
}