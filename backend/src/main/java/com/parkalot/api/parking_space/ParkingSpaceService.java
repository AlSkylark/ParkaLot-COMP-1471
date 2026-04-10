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
          ps.getSpaceType() != null
            ? SpaceType.values()[ps.getSpaceType()]
            : null
        )
      );
    });

    return result;
  }

  public GarageAvailability CheckAvailabilityForGarage(int id) {
    return repo
      .checkAvailability(id)
      .map(a -> {
        var ratio = (a.free * 100) / a.total;

        if (ratio > 70) return GarageAvailability.HIGH_AVAILABILITY;
        else if (ratio > 40) return GarageAvailability.MEDIUM_AVAILABILITY;
        else if (ratio > 0) return GarageAvailability.LOW_AVAILABILITY;
        else return GarageAvailability.FULL;
      })
      .orElse(GarageAvailability.FULL);
  }
}
