package com.parkalot.api.garage;

import com.parkalot.infrastructure.annotations.RestApiController;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestApiController
public class GarageController {

  private final GarageService service;

  public GarageController(GarageService service) {
    this.service = service;
  }

  @GetMapping("/garages")
  public ResponseEntity<List<GarageDto>> getMethodName() {
    var response = service.GetAllGarages();
    return ResponseEntity.ok(response);
  }
}
