package com.geo.i.resource.impl;

import com.geo.i.resource.ConnectionURL;
import com.geo.i.service.impl.ServicePage;
import com.geo.i.entity.UrlAdress;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Конфигурационный класс ControllerResources
 */
@Controller
@RequestMapping()
@RequiredArgsConstructor
public class ControllerResources {

  private final ServicePage service;

  @GetMapping()
  public String getStart(Model model) {
    model.addAttribute("urlAdress", new UrlAdress());
    return "startPage";
  }

  @PostMapping("/get")
  public String getPage(@ModelAttribute("urlAdress") UrlAdress urlAdress) {
    System.out.println("urlAdress: " + urlAdress);
    var urlCon = new ConnectionURL(urlAdress.getAddresses());
    service.save(urlCon);
    service.parseBlank(service.parseJSONBlank());
    return "redirect:/";
  }
}
