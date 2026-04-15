package com.parkalot.api.contracts;

import com.parkalot.api.contracts.dtos.ContractDetailDto;
import com.parkalot.api.contracts.dtos.ContractDto;
import com.parkalot.api.contracts.dtos.GuestData;
import com.parkalot.api.contracts.dtos.QuoteDto;
import com.parkalot.api.customer.CustomerRepository;
import com.parkalot.api.invoices.InvoiceRepository;
import com.parkalot.api.parking_space.ParkingSpaceService;
import com.parkalot.api.price_type.PriceBuilder;
import com.parkalot.api.space_reservation.SpaceReservationsService;
import com.parkalot.api.space_reservation.dtos.ReservationRequest;
import com.parkalot.infrastructure.enums.GarageAvailability;
import com.parkalot.infrastructure.models.Contract;
import com.parkalot.infrastructure.models.Invoice;
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
  private final InvoiceRepository invoiceRepo;

  public ContractService(
    ContractRepository repo,
    CustomerRepository customerRepo,
    ParkingSpaceService parkingSpaceService,
    ContractMapper mapper,
    SpaceReservationsService spaceReservationsService,
    InvoiceRepository invoiceRepo
  ) {
    this.repo = repo;
    this.customerRepo = customerRepo;
    this.parkingSpaceService = parkingSpaceService;
    this.mapper = mapper;
    this.spaceReservationsService = spaceReservationsService;
    this.invoiceRepo = invoiceRepo;
  }

  public Optional<QuoteDto> getQuote(String quoteNo) {
    return repo.findByContractNumber(quoteNo).map(c -> mapper.mapToQuote(c));
  }

  //TODO: Apply auto discounts like bulk reservations!
  public Optional<QuoteDto> createQuote(
    int garageId,
    ReservationRequest request
  ) {
    var contract = new Contract();

    //check garage availability first
    var availability = parkingSpaceService.CheckAvailabilityForGarage(
      garageId,
      request.spaceTypeId(),
      request
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
          request.address(),
          request.carPlate()
        );
        contract.setGuestData(guestData);
      }
    );

    // hold a space reservation
    // TODO: More than one space requested
    var spaceReservation = spaceReservationsService.assignParkingSpace(
      garageId,
      request,
      existingCustomer == null
    );
    contract.setSpaceReservations(Set.of(spaceReservation));

    //TODO: should be able to be chosen in UI
    contract.setRecurrent(false);

    contract.setQuote(true);
    contract.setDateCreated(LocalDateTime.now());

    //saves the contract and the new space reservation!
    spaceReservation.setContract(contract);
    repo.save(contract);

    var dto = mapper.mapToQuote(contract);

    return Optional.of(dto);
  }

  //TODO: This should return a ContractDto when the user confirms they are happy with the quote.
  public Optional<ContractDto> createContract(String contractNumber) {
    var result = repo.findByContractNumber(contractNumber);
    var contract = result.orElse(null);
    if (contract == null) {
      return Optional.empty();
    }

    //it's already been approved, just re-send confirmation;
    if (!contract.isQuote()) {
      var existing = mapper.mapToContractDto(contract);
      return Optional.of(existing);
    }

    var now = LocalDateTime.now();

    contract.setQuote(false);
    contract.setDateAgreed(now);
    repo.save(contract);

    //we'll create an invoice dated a month after it was agreed
    var invoice = new Invoice();
    invoice.setContract(contract);
    var invoiceCount = invoiceRepo.count();
    invoice.setInvoicenumber(String.format("%04d", invoiceCount + 1));
    invoice.setDatecreated(now);

    //TODO: We should also allow discounts at this point, and include in price builder!
    invoice.setDiscount(null);

    var spaceReservation = contract.getSpaceReservations().iterator().next();
    var price = PriceBuilder.calculateTotal(
      spaceReservation.getPricetype(),
      spaceReservation.getDatefrom(),
      spaceReservation.getDateto(),
      spaceReservation.getTimefrom(),
      spaceReservation.getTimeto()
    );
    invoice.setTotal(price);

    invoice.setIsrefund(false);

    invoiceRepo.save(invoice);

    var mapped = mapper.mapToContractDto(contract);
    return Optional.of(mapped);
  }

  public Optional<ContractDetailDto> getContractDetail(int id) {
    return repo.findById(id).map(c -> mapper.mapToContractDetail(c));
  }

  public void updateContract(int id, boolean isRecurrent, GuestData guestData) {
    var contract = repo.findById(id).orElseThrow();
    contract.setRecurrent(isRecurrent);
    if (contract.getGuestData() != null && guestData != null) {
      contract.setGuestData(guestData);
    }
    repo.save(contract);
  }

  public void cancelContract(int id) {
    repo.deleteById(id);
  }

  public void cancelContract(String contractNo) {
    repo.deleteByContractNumber(contractNo);
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
