package com.parkalot.api.garage;

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
    var list = repo.GetAll();
    var result = new ArrayList<GarageDto>();
    list.forEach(a -> {
      result.add(new GarageDto(a.Id, a.Name, a.getFormattedAddress()));
    });

    return result;
  }
}
