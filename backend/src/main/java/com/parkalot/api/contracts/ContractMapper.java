package com.parkalot.api.contracts;

import com.parkalot.api.contracts.dtos.QuoteDto;
import com.parkalot.api.price_type.PriceBuilder;
import com.parkalot.api.space_reservation.ReservationRequest;
import com.parkalot.infrastructure.models.Contract;
import org.springframework.stereotype.Component;

@Component
public class ContractMapper {

  public QuoteDto mapToQuote(Contract contract, ReservationRequest request) {
    var spaceReservation = contract.getSpaceReservations().iterator().next();
    var parkingSpace = spaceReservation.getSpace();
    var price = PriceBuilder.calculateTotal(
      spaceReservation.getPricetype(),
      request.startDate(),
      request.endDate(),
      request.startTime(),
      request.endTime()
    );

    var quote = new QuoteDto(
      request.fullName(),
      contract.getContractNumber(),
      parkingSpace.getCode(),
      parkingSpace.getFloor(),
      price,
      request.startDate(),
      request.endDate(),
      request.startTime(),
      request.endTime()
    );
    return quote;
  }
}
