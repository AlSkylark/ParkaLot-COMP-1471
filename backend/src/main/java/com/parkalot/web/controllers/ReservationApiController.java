package com.parkalot.web.controllers;

import com.parkalot.infrastructure.models.SpaceReservations;
import com.parkalot.infrastructure.repositories.SpaceReservationsRepository;
import com.parkalot.web.dto.ReservationRequest;

import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin
public class ReservationApiController {

    private final SpaceReservationsRepository repo;

    public ReservationApiController(SpaceReservationsRepository repo) {
        this.repo = repo;
    }

    // RESERVATION
    @PostMapping
    public Map<String, Object> createReservation(@RequestBody ReservationRequest request) {

        // Basic validation
        if (request.date == null) {
            return Map.of(
                    "success", false,
                    "message", "Dates are required"
            );
        }

        // entities
        SpaceReservations reservation = new SpaceReservations();

        reservation.setDate(request.date);
        reservation.setTime(request.time);
        reservation.setSpaceId(request.spaceId);
        reservation.setPriceTypeId(request.priceTypeId);
        reservation.setContractId(request.contractId);
        reservation.setCarId(request.carId);


        repo.save(reservation);

        // response
        return Map.of(
                "success", true,
                "message", "Reservation created successfully",
                "reservationId", reservation.getId()
        );
    }


    @GetMapping
    public List<SpaceReservations> getAllReservations() {
        return repo.findAll();
    }
}