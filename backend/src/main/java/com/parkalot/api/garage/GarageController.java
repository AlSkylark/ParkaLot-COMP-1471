package com.parkalot.api.garage;

import com.parkalot.api.DropdownItem;
import com.parkalot.api.contracts.ContractService;
import com.parkalot.api.parking_space.Dtos.ParkingSpaceCreateRequest;
import com.parkalot.api.parking_space.Dtos.ParkingSpaceDto;
import com.parkalot.api.parking_space.ParkingSpaceService;
import com.parkalot.api.scanner.dtos.ScannerDto;
import com.parkalot.api.space_reservation.SpaceReservationsService;
import com.parkalot.api.space_reservation.dtos.ReservationRequest;
import com.parkalot.api.space_reservation.dtos.SpaceReservationDto;
import com.parkalot.infrastructure.enums.ScannerType;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("garages")
public class GarageController {

  private final GarageService service;
  private final ContractService contractService;
  private final SpaceReservationsService reservationsService;
  private final ParkingSpaceService spaceService;

  public GarageController(
    GarageService service,
    ContractService contractService,
    SpaceReservationsService reservationsService,
    ParkingSpaceService spaceService
  ) {
    this.service = service;
    this.contractService = contractService;
    this.reservationsService = reservationsService;
    this.spaceService = spaceService;
  }

  @GetMapping("")
  public ResponseEntity<List<GarageDto>> getAll() {
    var response = service.GetAllGarages();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<GarageDto> get(@PathVariable int id) {
    var result = service.GetGarage(id);
    if (result.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(result.get());
  }

  @GetMapping("/dropdown")
  public List<DropdownItem> getDropdown() {
    return service.getForDropdown();
  }

  @PostMapping("/{id}/reserve")
  public ResponseEntity<?> requestReservation(
    @PathVariable int id,
    @RequestBody ReservationRequest request
  ) {
    // Basic validation
    if (request.startDate() == null || request.endDate() == null) {
      return ResponseEntity.badRequest().body(
        Map.of("success", false, "message", "Dates cannot be null!")
      );
    }

    var response = contractService.createQuote(id, request);
    if (response.isEmpty()) {
      return ResponseEntity.badRequest().body(
        Map.of(
          "success",
          false,
          "message",
          "No availability for the selected dates"
        )
      );
    }

    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}/reservations")
  public ResponseEntity<List<SpaceReservationDto>> getAllReservations(
    @PathVariable int id,
    @RequestParam LocalDate date
  ) {
    var result = reservationsService.getGarageReservations(id, date);

    return ResponseEntity.ok(result);
  }

  @GetMapping("/{id}/spaces")
  public ResponseEntity<List<ParkingSpaceDto>> getSpaces(@PathVariable int id) {
    return ResponseEntity.ok(spaceService.GetAllSpacesForGarage(id));
  }

  @PostMapping("/{id}/spaces")
  public ResponseEntity<ParkingSpaceDto> postMethodName(
    @PathVariable int id,
    @RequestBody ParkingSpaceCreateRequest request
  ) {
    var result = service.addSpace(id, request);

    return ResponseEntity.ok(result);
  }

  @GetMapping("/{id}/scanners")
  public ResponseEntity<List<ScannerDto>> getMethodName(@PathVariable int id) {
    var result = service.getScanners(id);

    return ResponseEntity.ok(result);
  }

  @PostMapping("/{id}/scanners")
  public ResponseEntity<ScannerDto> getMethodName(
    @PathVariable int id,
    @RequestBody Map.Entry<String, ScannerType> type
  ) {
    var result = service.addScanner(id, type.getValue().ordinal());

    return ResponseEntity.ok(result);
  }
}
