package com.parkalot.web.controllers;

import com.parkalot.infrastructure.helpers.EntityCard;
import com.parkalot.infrastructure.helpers.RepositoryFactory;
import com.parkalot.infrastructure.models.BaseModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {

  private final RepositoryFactory factory;

  public ItemController(RepositoryFactory factory) {
    this.factory = factory;
  }

  @GetMapping("/edit/{entity}")
  public String getAll(@PathVariable String entity, Model model) {
    var cards = EntityCard.GetCards();
    var card = cards.get(entity);
    var repo = factory.GetRepository(card.repositoryType());
    var items = repo.findAllByOrderByIdAsc();

    model.addAttribute("list", items);
    model.addAttribute("name", card.label());
    model.addAttribute("entity", entity);

    return "entity_templates/" + entity;
  }

  @PostMapping("/edit/{entity}")
  public String save(@PathVariable String entity, HttpServletRequest request) {
    var cards = EntityCard.GetCards();
    var card = cards.get(entity);

    var instance = (BaseModel) BeanUtils.instantiateClass(card.entityType());
    ServletRequestDataBinder binder = new ServletRequestDataBinder(instance);
    binder.bind(request);

    var repo = factory.GetRepository(card.repositoryType());

    repo.save(instance);

    return "redirect:/edit/" + entity;
  }

  @GetMapping("/delete/{entity}/{id}")
  public String delete(@PathVariable String entity, @PathVariable int id) {
    var cards = EntityCard.GetCards();
    var card = cards.get(entity);

    var repo = factory.GetRepository(card.repositoryType());
    repo.deleteById(id);

    return "redirect:/edit/" + entity;
  }
}
