package com.parkalot.api.space_reservation;

import com.parkalot.api.car.CarService;
import com.parkalot.api.parking_space.ParkingSpaceRepository;
import com.parkalot.api.price_type.PriceBuilder;
import com.parkalot.infrastructure.models.ParkingSpace;
import com.parkalot.infrastructure.models.SpaceReservation;
import org.springframework.stereotype.Service;

@Service
public class SpaceReservationsService {

  private final SpaceReservationsRepository repo;
  private final PriceBuilder priceBuilder;
  private final ParkingSpaceRepository parkingSpaceRepo;
  private final CarService carService;

  public SpaceReservationsService(
    SpaceReservationsRepository repo,
    ParkingSpaceRepository parkingSpaceRepo,
    PriceBuilder priceBuilder,
    CarService carService
  ) {
    this.repo = repo;
    this.parkingSpaceRepo = parkingSpaceRepo;
    this.priceBuilder = priceBuilder;
    this.carService = carService;
  }

  public SpaceReservation assignParkingSpace(
    int garageId,
    ReservationRequest request
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

    if (request.carPlate() != null && !request.carPlate().isBlank()) {
      var car = carService.getOrCreateCar(request.carPlate());
      spaceReservation.setCar(car);
    }

    return spaceReservation;
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
          request.startDate(),
          request.startTime(),
          request.endTime()
        )
        .getFirst();
    } else {
      availableSpace = parkingSpaceRepo
        .findAvailableSpace(garageId, request.startDate(), request.endDate())
        .getFirst();
    }

    return availableSpace;
  }
}
