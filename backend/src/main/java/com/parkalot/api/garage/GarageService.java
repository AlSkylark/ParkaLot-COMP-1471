package com.parkalot.api.garage;

import com.parkalot.api.DropdownItem;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GarageService {

  private final GarageRepository repo;

  public GarageService(GarageRepository repo) {
    this.repo = repo;
  }

  public List<GarageDto> GetAllGarages() {
    var list = repo.findAll();
    var result = new ArrayList<GarageDto>();
    list.forEach(a -> {
      result.add(new GarageDto(a.getId(), a.Name, a.getFormattedAddress()));
    });

    return result;
  }

  public List<DropdownItem> getForDropdown() {
    return repo
      .findAllByOrderByIdAsc()
      .stream()
      .map(g -> new DropdownItem(g.getId(), g.getName()))
      .toList();
  }
}
