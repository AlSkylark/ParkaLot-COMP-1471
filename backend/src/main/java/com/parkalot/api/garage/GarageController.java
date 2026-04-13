package com.parkalot.api.garage;

import com.parkalot.api.DropdownItem;
import com.parkalot.api.contracts.ContractService;
import com.parkalot.api.contracts.dtos.QuoteDto;
import com.parkalot.api.space_reservation.ReservationRequest;
import com.parkalot.infrastructure.models.SpaceReservation;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("garages")
public class GarageController {

  private final GarageService service;
  private final ContractService contractService;

  public GarageController(
    GarageService service,
    ContractService contractService
  ) {
    this.service = service;
    this.contractService = contractService;
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

  // RESERVATION
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

    // entities
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
    // response
    return ResponseEntity.ok(response);
  }
}
