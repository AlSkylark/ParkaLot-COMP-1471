package com.parkalot.api.garage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GarageRepository
  extends JpaRepository<com.parkalot.infrastructure.models.Garage, Integer> {}
