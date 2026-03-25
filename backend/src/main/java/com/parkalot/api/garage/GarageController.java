package com.parkalot.api.garage;

import com.parkalot.api.DropdownItem;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("garages")
public class GarageController {

  private final GarageService service;

  public GarageController(GarageService service) {
    this.service = service;
  }

  @GetMapping("/")
  public ResponseEntity<List<GarageDto>> getMethodName() {
    var response = service.GetAllGarages();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/dropdown")
  public List<DropdownItem> getDropdown() {
    return service.getForDropdown();
  }
}
