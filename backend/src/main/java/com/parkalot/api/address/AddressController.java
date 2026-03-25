package com.parkalot.api.address;

import com.parkalot.api.DropdownItem;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

  private final AddressRepository repo;

  public AddressController(AddressRepository repo) {
    this.repo = repo;
  }

  @GetMapping("/addresses/dropdown")
  public List<DropdownItem> getForDropdown() {
    var addresses = repo.findAll();

    var list = new ArrayList<DropdownItem>();
    for (var address : addresses) {
      list.add(new DropdownItem(address.getId(), address.toString()));
    }

    return list;
  }
}
