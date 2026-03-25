package com.parkalot.api.parking_space;

import com.parkalot.api.DropdownItem;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("parking-spaces")
public class ParkingSpaceController {

  private final ParkingSpaceRepository repo;

  public ParkingSpaceController(ParkingSpaceRepository repo) {
    this.repo = repo;
  }

  @GetMapping("/dropdown")
  public List<DropdownItem> getDropdown() {
    return repo
      .findAllByOrderByIdAsc()
      .stream()
      .map(p -> new DropdownItem(p.getId(), p.getCode()))
      .toList();
  }
}
