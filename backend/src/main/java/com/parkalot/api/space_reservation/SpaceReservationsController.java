package com.parkalot.api.space_reservation;

import com.parkalot.api.space_reservation.dtos.ReservationUpdateRequest;
import com.parkalot.api.space_reservation.dtos.SpaceReservationDto;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reservations")
@CrossOrigin
public class SpaceReservationsController {

  private final SpaceReservationsService service;

  public SpaceReservationsController(SpaceReservationsService service) {
    this.service = service;
  }

  @GetMapping
  public List<SpaceReservationDto> getAllReservations(
    @RequestParam(required = false) LocalDate date
  ) {
    return service.getAllReservations(date);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateReservation(
    @PathVariable int id,
    @RequestBody ReservationUpdateRequest request
  ) {
    service.updateReservation(id, request);
    return ResponseEntity.ok(Map.of("success", true));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> cancelReservation(@PathVariable int id) {
    service.cancelReservation(id);
    return ResponseEntity.ok(Map.of("success", true));
  }
}
