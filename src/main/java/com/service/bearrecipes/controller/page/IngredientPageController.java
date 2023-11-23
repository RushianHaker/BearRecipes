package com.service.bearrecipes.controller.page;

import com.service.bearrecipes.service.ReceiptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IngredientPageController {
    private final ReceiptService receiptService;

    public IngredientPageController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping({"/ingredient/addingredient/{receiptId}"})
    public String addIngredientPage(@PathVariable("receiptId") long receiptId, Model model) {
        model.addAttribute("receiptDTO", receiptService.findById(receiptId));
        return "addingredient";
    }
}
