package com.parkalot.web.controllers;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/")
  public String home(Model model) {
    var list = new ArrayList<EntityCard>();
    list.add(new EntityCard("Customers", "customers"));
    list.add(new EntityCard("Addresses", "addresses"));
    list.add(new EntityCard("Garages", "garages"));
    list.add(new EntityCard("Parking spaces", "parkingspaces"));
    list.add(new EntityCard("Scanners", "scanner"));
    list.add(new EntityCard("Sensor devices", "sensordevice"));
    list.add(new EntityCard("Price types", "pricetypes"));
    list.add(new EntityCard("Discounts", "discounts"));

    model.addAttribute("internal_admin_items", list);

    return "index";
  }
}

record EntityCard(String name, String dbName) {}
