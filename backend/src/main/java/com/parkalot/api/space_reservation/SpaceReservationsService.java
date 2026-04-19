package com.parkalot.api.space_reservation;

import com.parkalot.api.car.CarService;
import com.parkalot.api.parking_space.ParkingSpaceRepository;
import com.parkalot.api.price_type.PriceBuilder;
import com.parkalot.api.space_reservation.dtos.ReservationRequest;
import com.parkalot.api.space_reservation.dtos.ReservationUpdateRequest;
import com.parkalot.api.space_reservation.dtos.SpaceReservationDto;
import com.parkalot.infrastructure.models.ParkingSpace;
import com.parkalot.infrastructure.models.SpaceReservation;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SpaceReservationsService {

  private final SpaceReservationsRepository repo;
  private final PriceBuilder priceBuilder;
  private final ParkingSpaceRepository parkingSpaceRepo;
  private final CarService carService;
  private final SpaceReservationsMapper mapper;

  public SpaceReservationsService(
    SpaceReservationsRepository repo,
    SpaceReservationsMapper mapper,
    ParkingSpaceRepository parkingSpaceRepo,
    PriceBuilder priceBuilder,
    CarService carService
  ) {
    this.repo = repo;
    this.mapper = mapper;
    this.parkingSpaceRepo = parkingSpaceRepo;
    this.priceBuilder = priceBuilder;
    this.carService = carService;
  }

  public SpaceReservation assignParkingSpace(
    int garageId,
    ReservationRequest request,
    boolean hasCustomer
  ) {
    var spaceReservation = new SpaceReservation();

    spaceReservation.setSpace(getAvailableSpace(garageId, request));

    spaceReservation.setDatefrom(request.startDate());
    spaceReservation.setDateto(request.endDate());
    spaceReservation.setTimefrom(request.startTime());
    spaceReservation.setTimeto(request.endTime());

    var generatedPriceType = priceBuilder
      .days(request.startDate(), request.endDate())
      .hours(request.startTime(), request.endTime())
      .spaceType(request.spaceTypeId())
      .generatePriceType();

    spaceReservation.setPricetype(generatedPriceType);

    if (
      hasCustomer && request.carPlate() != null && !request.carPlate().isBlank()
    ) {
      var car = carService.getCar(request.carPlate());
      spaceReservation.setCar(car);
    }

    return spaceReservation;
  }

  public List<SpaceReservationDto> getGarageReservations(
    int garageId,
    LocalDate date
  ) {
    var dateToSearch = date;
    if (date == null) {
      dateToSearch = LocalDate.now();
    }
    var reservations = repo.findByGarageId(garageId, dateToSearch);
    var mapped = reservations
      .stream()
      .map(r -> mapper.mapToReservationDto(r))
      .toList();

    return mapped;
  }

  public List<SpaceReservationDto> getAllReservations(LocalDate date) {
    var dateToSearch = date != null ? date : LocalDate.now();
    return repo
      .findByDate(dateToSearch)
      .stream()
      .map(r -> mapper.mapToReservationDto(r))
      .toList();
  }

  public void updateReservation(int id, ReservationUpdateRequest request) {
    var reservation = repo.findById(id).orElseThrow();
    reservation.setDatefrom(request.datefrom());
    reservation.setDateto(request.dateto());
    reservation.setTimefrom(request.timefrom());
    reservation.setTimeto(request.timeto());
    repo.save(reservation);
  }

  public void cancelReservation(int reservationId) {
    repo.deleteById(reservationId);
  }

  private ParkingSpace getAvailableSpace(
    int garageId,
    ReservationRequest request
  ) {
    ParkingSpace availableSpace;
    if (request.startTime() != null && request.endTime() != null) {
      availableSpace = parkingSpaceRepo
        .findAvailableSpace(
          garageId,
          request.spaceTypeId().ordinal(),
          request.startDate(),
          request.startTime(),
          request.endTime()
        )
        .getFirst();
    } else {
      availableSpace = parkingSpaceRepo
        .findAvailableSpace(
          garageId,
          request.spaceTypeId().ordinal(),
          request.startDate(),
          request.endDate()
        )
        .getFirst();
    }

    return availableSpace;
  }
}
