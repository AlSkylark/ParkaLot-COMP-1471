package com.parkalot.api.garage;

import com.parkalot.api.DropdownItem;
import com.parkalot.api.parking_space.Dtos.ParkingSpaceCreateRequest;
import com.parkalot.api.parking_space.Dtos.ParkingSpaceDto;
import com.parkalot.api.parking_space.ParkingSpaceService;
import com.parkalot.api.scanner.ScannerRepository;
import com.parkalot.api.scanner.dtos.ScannerDto;
import com.parkalot.infrastructure.enums.ScannerType;
import com.parkalot.infrastructure.enums.SensorStatus;
import com.parkalot.infrastructure.enums.SpaceType;
import com.parkalot.infrastructure.models.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class GarageService {

  private final GarageRepository repo;
  private final ParkingSpaceService parkingSpaceService;
  private final ScannerRepository scannerRepo;

  public GarageService(
    GarageRepository repo,
    ParkingSpaceService parkingSpaceService,
    ScannerRepository scannerRepo
  ) {
    this.repo = repo;
    this.parkingSpaceService = parkingSpaceService;
    this.scannerRepo = scannerRepo;
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

  public ParkingSpaceDto addSpace(
    int garageId,
    ParkingSpaceCreateRequest request
  ) {
    var garage = repo.findById(garageId).orElseThrow();
    var newSpace = parkingSpaceService.addSpace(garage, request);
    repo.save(garage);

    var dto = new ParkingSpaceDto(
      newSpace.getId(),
      newSpace.getCode(),
      newSpace.getFloor(),
      SpaceType.values()[newSpace.getSpacetype()],
      SensorStatus.values()[newSpace.getSensor().getStatus()]
    );

    return dto;
  }

  public List<ScannerDto> getScanners(int garageId) {
    return repo
      .findById(garageId)
      .map(entity ->
        new ArrayList<>(
          entity
            .getScanners()
            .stream()
            .map(s ->
              new ScannerDto(
                s.getId(),
                ScannerType.values()[s.getScannertype()]
              )
            )
            .toList()
        )
      )
      .orElse(new ArrayList<>());
  }

  public ScannerDto addScanner(int garageId, int type) {
    var garage = repo.findById(garageId).orElseThrow();

    var newScanner = new Scanner();
    newScanner.setGarage(garage);
    newScanner.setScannertype(type);
    scannerRepo.save(newScanner);

    garage.getScanners().add(newScanner);
    repo.save(garage);

    return new ScannerDto(
      newScanner.getId(),
      ScannerType.values()[newScanner.getScannertype()]
    );
  }
}
