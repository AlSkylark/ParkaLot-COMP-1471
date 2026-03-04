package com.parkalot.api.garage;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GarageRepository
  extends JpaRepository<com.parkalot.infrastructure.models.Garage, Integer>
{
  @Query("SELECT g FROM com.parkalot.infrastructure.models.Garage g")
  public List<com.parkalot.infrastructure.models.Garage> GetAll();
}
