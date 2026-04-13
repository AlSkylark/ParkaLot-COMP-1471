package com.parkalot.api.space_reservation;

import com.parkalot.infrastructure.models.SpaceReservation;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reservations")
@CrossOrigin
public class SpaceReservationsController {

  private final SpaceReservationsRepository repo;

  public SpaceReservationsController(SpaceReservationsRepository repo) {
    this.repo = repo;
  }

  @GetMapping
  public List<SpaceReservation> getAllReservations() {
    return repo.findAll();
  }
}
