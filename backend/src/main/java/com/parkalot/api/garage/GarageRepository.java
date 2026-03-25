package com.parkalot.api.garage;

import com.parkalot.infrastructure.models.Garage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GarageRepository extends JpaRepository<Garage, Integer> {}
