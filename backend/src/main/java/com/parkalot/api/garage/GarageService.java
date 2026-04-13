package com.parkalot.api.garage;

import com.parkalot.api.DropdownItem;
import com.parkalot.api.parking_space.ParkingSpaceService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class GarageService {

  private final GarageRepository repo;
  private final ParkingSpaceService parkingSpaceService;

  public GarageService(
    GarageRepository repo,
    ParkingSpaceService parkingSpaceService
  ) {
    this.repo = repo;
    this.parkingSpaceService = parkingSpaceService;
  }

  public List<GarageDto> GetAllGarages() {
    var list = repo.findAll();
    var result = new ArrayList<GarageDto>();
    list.forEach(a -> {
      var availability = parkingSpaceService.CheckAvailabilityForGarage(
        a.getId()
      );
      result.add(
        new GarageDto(
          a.getId(),
          a.name,
          a.getFormattedAddress(),
          availability,
          Optional.empty()
        )
      );
    });

    return result;
  }

  public Optional<GarageDto> GetGarage(int id) {
    var result = repo.findById(id);

    return result.map(g -> {
      var availability = parkingSpaceService.CheckAvailabilityForGarage(id);
      var spaces = parkingSpaceService.GetAllSpacesForGarage(id);
      return new GarageDto(
        g.getId(),
        g.getName(),
        g.getFormattedAddress(),
        availability,
        Optional.of(spaces)
      );
    });
  }

  public List<DropdownItem> getForDropdown() {
    return repo
      .findAllByOrderByIdAsc()
      .stream()
      .map(g -> new DropdownItem(g.getId(), g.getName()))
      .toList();
  }
}
