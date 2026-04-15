package com.parkalot.api.space_reservation;

import com.parkalot.api.space_reservation.dtos.SpaceReservationDto;
import com.parkalot.infrastructure.models.SpaceReservation;
import org.springframework.stereotype.Component;

@Component
public class SpaceReservationsMapper {

  public SpaceReservationDto mapToReservationDto(SpaceReservation reservation) {
    var garage = reservation.getSpace().getGarage();
    var contract = reservation.getContract();

    return new SpaceReservationDto(
      reservation.getId(),
      contract.getId(),
      contract.getCustomerFullName(),
      reservation.getSpace().getCode(),
      reservation.getSpace().getFloor(),
      reservation.getDatefrom(),
      reservation.getDateto(),
      reservation.getTimefrom(),
      reservation.getTimeto(),
      reservation.getCar() != null ? reservation.getCar().getPlateno() : null,
      reservation.getPricetype().getName(),
      garage != null ? garage.getName() : null
    );
  }
}
