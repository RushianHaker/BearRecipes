package com.service.bearrecipes.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MenuPageController {
  @GetMapping({ "/" })
  public String getMenuPage() {
    return "menu";
  }
}
