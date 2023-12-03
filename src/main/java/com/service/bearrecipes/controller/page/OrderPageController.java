package com.service.bearrecipes.controller.page;

import com.service.bearrecipes.service.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderPageController {
    private final StockService stockService;

    public OrderPageController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping({"/order"})
    public String getAllOrders() {
        return "orderlist";
    }

    @GetMapping({"/order/addorder"})
    public String addOrderPage(Model model) {
        model.addAttribute("stocks", stockService.findAll());
        return "addorder";
    }

}
