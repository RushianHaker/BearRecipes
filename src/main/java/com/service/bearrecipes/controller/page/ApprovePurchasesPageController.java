package com.service.bearrecipes.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ApprovePurchasesPageController {

  @GetMapping({ "/approvepurchases" })
  public String approvePurchases() {
    return "approvepurchases";
  }
}
