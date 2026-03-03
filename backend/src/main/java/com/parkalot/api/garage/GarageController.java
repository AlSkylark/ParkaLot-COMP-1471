package com.parkalot.api.garage;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GarageController {

  private final GarageService service;

  public GarageController(GarageService service) {
    this.service = service;
  }

  @GetMapping("/garages")
  public List<Garage> getMethodName() {
    var response = service.GetAllGarages();
    return response;
  }
}
