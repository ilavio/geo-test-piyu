package com.geo.i.resource.impl;

import com.geo.i.resource.ConnectionURL;
import com.geo.i.service.impl.ServicePage;
import com.geo.i.service.impl.UrlAdress;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class ControllerResources {

  @GetMapping()
  public String getStart(Model model) {
    model.addAttribute("urlAdress", new UrlAdress());
    return "startPage";
  }

  @PostMapping("/get")
  public String getPage(@ModelAttribute("urlAdress") UrlAdress urlAdress) {
    System.out.println("urlAdress: " + urlAdress);
    var urlCon = new ConnectionURL(urlAdress.getAddresses());
    var service = new ServicePage();
    service.save(urlCon);
    return "redirect:/url";
  }
}
