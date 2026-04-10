package com.parkalot.web.controllers;

import com.parkalot.infrastructure.models.SpaceReservations;
import com.parkalot.infrastructure.repositories.SpaceReservationsRepository;
import com.parkalot.web.dto.ReservationRequest;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservationApiController {

    private final SpaceReservationsRepository repo;

    public ReservationApiController(SpaceReservationsRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Object createReservation(@RequestBody ReservationRequest request) {

        if (request.dateFrom == null || request.dateTo == null) {
            return Map.of("success", false, "message", "Dates are required");
        }

        SpaceReservations reservation = new SpaceReservations();

        reservation.setDateFrom(request.dateFrom);
        reservation.setDateTo(request.dateTo);
        reservation.setTimeFrom(request.timeFrom);
        reservation.setTimeTo(request.timeTo);

        reservation.setSpaceId(request.spaceId);
        reservation.setPriceTypeId(request.priceTypeId);
        reservation.setContractId(request.contractId);

        repo.save(reservation);

        return Map.of(
                "success", true,
                "message", "Reservation created successfully",
                "reservationId", reservation.getId()
        );
    }
}