package com.parkalot.api.parking_space;

import com.parkalot.api.DropdownItem;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("parking-spaces")
public class ParkingSpaceController {

  private final ParkingSpaceService service;

  public ParkingSpaceController(ParkingSpaceService service) {
    this.service = service;
  }

  @GetMapping("/dropdown")
  public List<DropdownItem> getDropdown() {
    return service.getAsDropdownItems();
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable int id) {
    if (service.deleteSpace(id)) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.badRequest().build();
  }
}
