package com.parkalot.infrastructure;

import com.parkalot.infrastructure.models.SpaceReservations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpaceReservationsRepository extends JpaRepository<SpaceReservations, Integer> {
}