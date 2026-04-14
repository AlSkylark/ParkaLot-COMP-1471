package com.parkalot.web.controllers;

import com.parkalot.infrastructure.helpers.EntityCard;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("internal_admin_items", EntityCard.GetCards());
    return "index";
  }

  @GetMapping("/garages/manage")
  public String garageManagement(Model model) {
    model.addAttribute("internal_admin_items", EntityCard.GetCards());
    return "garage_management";
  }

  @GetMapping("/reservations")
  public String reservationsManagement(Model model) {
    model.addAttribute("internal_admin_items", EntityCard.GetCards());
    return "reservations";
  }

  @GetMapping("/reservations/{id}")
  public String reservationDetail(@PathVariable int id, Model model) {
    model.addAttribute("internal_admin_items", EntityCard.GetCards());
    model.addAttribute("contractId", id);
    return "reservation_detail";
  }
}
