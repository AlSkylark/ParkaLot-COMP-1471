package com.parkalot.api.contracts;

import com.parkalot.api.contracts.dtos.ContractDetailDto;
import com.parkalot.api.contracts.dtos.ContractDto;
import com.parkalot.api.contracts.dtos.QuoteDto;
import com.parkalot.api.price_type.PriceBuilder;
import com.parkalot.api.space_reservation.SpaceReservationsMapper;
import com.parkalot.api.space_reservation.dtos.SpaceReservationDto;
import com.parkalot.infrastructure.models.Contract;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ContractMapper {

  private final SpaceReservationsMapper spaceReservationsMapper;

  public ContractMapper(SpaceReservationsMapper spaceReservationsMapper) {
    this.spaceReservationsMapper = spaceReservationsMapper;
  }

  public QuoteDto mapToQuote(Contract contract) {
    //TODO: A contract could have several reservations!
    var spaceReservation = contract.getSpaceReservations().iterator().next();

    var parkingSpace = spaceReservation.getSpace();
    var price = PriceBuilder.calculateTotal(
      spaceReservation.getPricetype(),
      spaceReservation.getDatefrom(),
      spaceReservation.getDateto(),
      spaceReservation.getTimefrom(),
      spaceReservation.getTimeto()
    );

    var quote = new QuoteDto(
      contract.getCustomerFullName(),
      contract.getContractNumber(),
      parkingSpace.getCode(),
      parkingSpace.getFloor(),
      parkingSpace.getSpacetype(),
      price,
      spaceReservation.getDatefrom(),
      spaceReservation.getDateto(),
      spaceReservation.getTimefrom(),
      spaceReservation.getTimeto()
    );
    return quote;
  }

  public ContractDto mapToContractDto(Contract contract) {
    var invoice = contract.getInvoices().iterator().next();
    return new ContractDto(
      mapToQuote(contract),
      ChronoUnit.DAYS.addTo(invoice.getDatecreated().toLocalDate(), 30)
    );
  }

  public ContractDetailDto mapToContractDetail(Contract contract) {
    String customerName = null;
    if (contract.getCustomer() != null) {
      customerName = contract.getCustomer().getFullName();
    }

    List<SpaceReservationDto> reservations = contract
      .getSpaceReservations()
      .stream()
      .map(r -> spaceReservationsMapper.mapToReservationDto(r))
      .toList();

    return new ContractDetailDto(
      contract.getId(),
      contract.getContractNumber(),
      contract.getDateCreated(),
      contract.getDateAgreed(),
      contract.isQuote(),
      contract.isRecurrent(),
      customerName,
      contract.getGuestData(),
      reservations
    );
  }
}
