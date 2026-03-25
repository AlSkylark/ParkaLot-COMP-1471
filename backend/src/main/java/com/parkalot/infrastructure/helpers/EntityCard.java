package com.parkalot.infrastructure.helpers;

import com.parkalot.api.address.AddressRepository;
import com.parkalot.api.customer.CustomerRepository;
import com.parkalot.api.discount.DiscountRepository;
import com.parkalot.api.garage.GarageRepository;
import com.parkalot.api.parking_space.ParkingSpaceRepository;
import com.parkalot.api.price_type.PriceTypeRepository;
import com.parkalot.api.scanner.ScannerRepository;
import com.parkalot.api.sensor_device.SensorDeviceRepository;
import com.parkalot.infrastructure.models.Address;
import com.parkalot.infrastructure.models.Customer;
import com.parkalot.infrastructure.models.Discount;
import com.parkalot.infrastructure.models.Garage;
import com.parkalot.infrastructure.models.ParkingSpace;
import com.parkalot.infrastructure.models.PriceType;
import com.parkalot.infrastructure.models.Scanner;
import com.parkalot.infrastructure.models.SensorDevice;
import java.util.HashMap;
import java.util.Map;

public record EntityCard(
  String label,
  Class<?> repositoryType,
  Class<?> entityType
) {
  private static Map<String, EntityCard> _cached = null;

  public static Map<String, EntityCard> GetCards() {
    if (_cached != null) {
      return _cached;
    }

    var map = new HashMap<String, EntityCard>() {};

    map.put(
      "customers",
      new EntityCard("Customers", CustomerRepository.class, Customer.class)
    );
    map.put(
      "addresses",
      new EntityCard("Addresses", AddressRepository.class, Address.class)
    );
    map.put(
      "garages",
      new EntityCard("Garages", GarageRepository.class, Garage.class)
    );
    map.put(
      "parkingspaces",
      new EntityCard(
        "Parking spaces",
        ParkingSpaceRepository.class,
        ParkingSpace.class
      )
    );
    map.put(
      "scanner",
      new EntityCard("Scanners", ScannerRepository.class, Scanner.class)
    );
    map.put(
      "sensordevice",
      new EntityCard(
        "Sensor devices",
        SensorDeviceRepository.class,
        SensorDevice.class
      )
    );
    map.put(
      "pricetypes",
      new EntityCard("Price types", PriceTypeRepository.class, PriceType.class)
    );
    map.put(
      "discounts",
      new EntityCard("Discounts", DiscountRepository.class, Discount.class)
    );

    _cached = map;
    return map;
  }
}
