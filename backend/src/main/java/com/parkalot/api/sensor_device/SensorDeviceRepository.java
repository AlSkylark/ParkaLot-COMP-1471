package com.parkalot.api.sensor_device;

import com.parkalot.infrastructure.models.SensorDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorDeviceRepository
  extends JpaRepository<SensorDevice, Integer> {}
