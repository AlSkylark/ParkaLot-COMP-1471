package com.parkalot.api;

import com.parkalot.infrastructure.enums.DiscountType;
import com.parkalot.infrastructure.enums.ScannerType;
import com.parkalot.infrastructure.enums.SensorStatus;
import com.parkalot.infrastructure.enums.SpaceType;
import com.parkalot.infrastructure.enums.Title;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enums")
public class EnumController {

  @GetMapping("/titles")
  public List<DropdownItem> getTitles() {
    return Arrays.stream(Title.values())
      .map(t -> new DropdownItem(t.ordinal(), t.getLabel()))
      .toList();
  }

  @GetMapping("/space-types")
  public List<DropdownItem> getSpaceTypes() {
    return Arrays.stream(SpaceType.values())
      .map(t -> new DropdownItem(t.ordinal(), t.getLabel()))
      .toList();
  }

  @GetMapping("/discount-types")
  public List<DropdownItem> getDiscountTypes() {
    return Arrays.stream(DiscountType.values())
      .map(t -> new DropdownItem(t.ordinal(), t.getLabel()))
      .toList();
  }

  @GetMapping("/scanner-types")
  public List<DropdownItem> getScannerTypes() {
    return Arrays.stream(ScannerType.values())
      .map(t -> new DropdownItem(t.ordinal(), t.getLabel()))
      .toList();
  }

  @GetMapping("/sensor-statuses")
  public List<DropdownItem> getSensorStatuses() {
    return Arrays.stream(SensorStatus.values())
      .map(t -> new DropdownItem(t.ordinal(), t.getLabel()))
      .toList();
  }
}
