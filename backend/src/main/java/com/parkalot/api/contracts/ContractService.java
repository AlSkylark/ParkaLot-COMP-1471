package com.parkalot.api.contracts;

import com.parkalot.api.contracts.dtos.GuestData;
import com.parkalot.api.contracts.dtos.QuoteDto;
import com.parkalot.api.customer.CustomerRepository;
import com.parkalot.api.parking_space.ParkingSpaceService;
import com.parkalot.api.space_reservation.ReservationRequest;
import com.parkalot.api.space_reservation.SpaceReservationsService;
import com.parkalot.infrastructure.enums.GarageAvailability;
import com.parkalot.infrastructure.models.Contract;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;

@Service
public class ContractService {

  private final ContractRepository repo;
  private final CustomerRepository customerRepo;
  private final ParkingSpaceService parkingSpaceService;
  private final ContractMapper mapper;
  private final SpaceReservationsService spaceReservationsService;

  public ContractService(
    ContractRepository repo,
    CustomerRepository customerRepo,
    ParkingSpaceService parkingSpaceService,
    ContractMapper mapper,
    SpaceReservationsService spaceReservationsService
  ) {
    this.repo = repo;
    this.customerRepo = customerRepo;
    this.parkingSpaceService = parkingSpaceService;
    this.mapper = mapper;
    this.spaceReservationsService = spaceReservationsService;
  }

  public Optional<QuoteDto> createQuote(
    int garageId,
    ReservationRequest request
  ) {
    var contract = new Contract();

    //check garage availability first
    var availability = parkingSpaceService.CheckAvailabilityForGarage(
      garageId,
      request.spaceTypeId()
    );
    if (availability == GarageAvailability.FULL) {
      return Optional.empty();
    }

    var contractNo = generateContractNumber(6);
    contract.setContractNumber(contractNo);

    var existingCustomer = customerRepo.findByEmail(request.email());
    existingCustomer.ifPresentOrElse(
      c -> contract.setCustomer(c),
      () -> {
        var guestData = new GuestData(
          request.fullName(),
          request.email(),
          request.address()
        );
        contract.setGuestData(guestData);
      }
    );

    // hold a space reservation
    // TODO: More than one space requested
    var spaceReservation = spaceReservationsService.assignParkingSpace(
      garageId,
      request
    );
    contract.setSpaceReservations(Set.of(spaceReservation));

    //TODO: should be able to be chosen in UI
    contract.setRecurrent(false);

    contract.setQuote(true);
    contract.setDateCreated(LocalDateTime.now());

    //saves the contract and the new space reservation!
    spaceReservation.setContract(contract);
    repo.save(contract);

    var dto = mapper.mapToQuote(contract, request);

    return Optional.of(dto);
  }

  //TODO: This should return a ContractDto when the user confirms they are happy with the quote.
  public Contract createContract(String contractNumber) {
    var result = repo.findByContractNumber(contractNumber);
    var contract = result.orElseThrow();

    contract.setQuote(false);
    contract.setDateAgreed(LocalDateTime.now());
    repo.save(contract);

    return contract;
  }

  public String generateContractNumber(int length) {
    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    Random random = new Random();
    var result = IntStream.range(0, length)
      .mapToObj(i ->
        String.valueOf(chars.charAt(random.nextInt(chars.length())))
      )
      .collect(Collectors.joining());

    var existing = repo.findByContractNumber(result);

    if (existing.isPresent()) return generateContractNumber(length + 1);
    return result;
  }
}
