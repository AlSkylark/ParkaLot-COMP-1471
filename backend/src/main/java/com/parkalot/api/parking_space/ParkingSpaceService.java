package com.parkalot.api.parking_space;

import com.parkalot.api.parking_space.Dtos.ParkingSpaceDto;
import com.parkalot.infrastructure.enums.GarageAvailability;
import com.parkalot.infrastructure.enums.SpaceType;
import java.util.ArrayList;
import java.util.List;
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
          ps.getCode(),
          ps.getFloor(),
          ps.getSpacetype() != null
            ? SpaceType.values()[ps.getSpacetype()]
            : null
        )
      );
    });

    return result;
  }

  public GarageAvailability CheckAvailabilityForGarage(int id) {
    return repo
      .checkAvailability(id)
      .map(a -> CalculateAvailability(a.free, a.total))
      .orElse(GarageAvailability.FULL);
  }

  public GarageAvailability CheckAvailabilityForGarage(int id, SpaceType type) {
    return repo
      .checkAvailability(id, type.ordinal())
      .map(a -> CalculateAvailability(a.free, a.total))
      .orElse(GarageAvailability.FULL);
  }

  private GarageAvailability CalculateAvailability(int free, int total) {
    var ratio = (free * 100) / total;

    if (ratio > 70) return GarageAvailability.HIGH_AVAILABILITY;
    else if (ratio > 40) return GarageAvailability.MEDIUM_AVAILABILITY;
    else if (ratio > 0) return GarageAvailability.LOW_AVAILABILITY;
    else return GarageAvailability.FULL;
  }
}
