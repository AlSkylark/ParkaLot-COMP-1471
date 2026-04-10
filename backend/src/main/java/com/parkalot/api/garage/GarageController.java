package com.parkalot.api.garage;

import com.parkalot.api.DropdownItem;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("garages")
public class GarageController {

  private final GarageService service;

  public GarageController(GarageService service) {
    this.service = service;
  }

  @GetMapping("")
  public ResponseEntity<List<GarageDto>> getAll() {
    var response = service.GetAllGarages();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<GarageDto> get(@PathVariable int id) {
    var result = service.GetGarage(id);
    if (result.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(result.get());
  }

  @GetMapping("/dropdown")
  public List<DropdownItem> getDropdown() {
    return service.getForDropdown();
  }
}
