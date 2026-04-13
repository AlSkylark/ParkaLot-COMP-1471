package com.parkalot.api.parking_space;

import com.parkalot.api.DropdownItem;
import com.parkalot.api.parking_space.Dtos.ParkingAvailabilityDto;
import com.parkalot.api.parking_space.Dtos.ParkingSpaceCreateRequest;
import com.parkalot.api.parking_space.Dtos.ParkingSpaceDto;
import com.parkalot.api.space_reservation.dtos.ReservationRequest;
import com.parkalot.infrastructure.enums.GarageAvailability;
import com.parkalot.infrastructure.enums.SensorStatus;
import com.parkalot.infrastructure.enums.SpaceType;
import com.parkalot.infrastructure.models.Garage;
import com.parkalot.infrastructure.models.ParkingSpace;
import com.parkalot.infrastructure.models.SensorDevice;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ParkingSpaceService {

  private final ParkingSpaceRepository repo;

  public ParkingSpaceService(ParkingSpaceRepository repo) {
    this.repo = repo;
  }

  public List<ParkingSpaceDto> GetAllSpacesForGarage(int id) {
    var spaces = repo.findByGarageId(id);
    var result = new ArrayList<ParkingSpaceDto>();

    spaces.forEach(ps -> {
      result.add(
        new ParkingSpaceDto(
          ps.getId(),
          ps.getCode(),
          ps.getFloor(),
          ps.getSpacetype() != null
            ? SpaceType.values()[ps.getSpacetype()]
            : null,
          ps.getSensor() != null
            ? SensorStatus.values()[ps.getSensor().getStatus()]
            : SensorStatus.FREE
        )
      );
    });

    return result;
  }

  public GarageAvailability CheckAvailabilityForGarage(int id) {
    return repo
      .checkCurrentAvailability(id)
      .map(a -> CalculateAvailability(a.free, a.total))
      .orElse(GarageAvailability.FULL);
  }

  public GarageAvailability CheckAvailabilityForGarage(int id, SpaceType type) {
    return repo
      .checkCurrentAvailability(id, type.ordinal())
      .map(a -> CalculateAvailability(a.free, a.total))
      .orElse(GarageAvailability.FULL);
  }

  public GarageAvailability CheckAvailabilityForGarage(
    int id,
    SpaceType type,
    ReservationRequest request
  ) {
    Optional<ParkingAvailabilityDto> availability;
    if (request.startTime() != null && request.endTime() != null) {
      availability = repo.checkDayAvailability(
        id,
        type.ordinal(),
        request.startDate(),
        request.startTime(),
        request.endTime()
      );
    } else {
      availability = repo.checkDateRangeAvailability(
        id,
        type.ordinal(),
        request.startDate(),
        request.endDate()
      );
    }

    return availability
      .map(a -> CalculateAvailability(a.free, a.total))
      .orElse(GarageAvailability.FULL);
  }

  public List<DropdownItem> getAsDropdownItems() {
    return repo
      .findAllByOrderByIdAsc()
      .stream()
      .map(p -> new DropdownItem(p.getId(), p.getCode()))
      .toList();
  }

  public ParkingSpace addSpace(
    Garage garage,
    ParkingSpaceCreateRequest request
  ) {
    var newSpace = new ParkingSpace();
    newSpace.setCode(request.code());
    newSpace.setFloor(request.floor());
    newSpace.setGarage(garage);
    newSpace.setSpacetype(request.type().ordinal());

    var newSensor = new SensorDevice();
    newSensor.setParkingspace(newSpace);
    newSensor.setStatus(0);
    newSpace.setSensor(newSensor);

    repo.save(newSpace);

    return newSpace;
  }

  public boolean deleteSpace(int id) {
    try {
      repo.deleteById(id);
      return true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  private GarageAvailability CalculateAvailability(int free, int total) {
    var ratio = (free * 100) / total;

    if (ratio > 70) return GarageAvailability.HIGH_AVAILABILITY;
    else if (ratio > 40) return GarageAvailability.MEDIUM_AVAILABILITY;
    else if (ratio > 0) return GarageAvailability.LOW_AVAILABILITY;
    else return GarageAvailability.FULL;
  }
}
