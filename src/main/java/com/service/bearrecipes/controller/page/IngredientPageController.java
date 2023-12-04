package com.service.bearrecipes.controller.page;

import com.service.bearrecipes.service.IngredientService;
import com.service.bearrecipes.service.ReceiptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IngredientPageController {
    private final ReceiptService receiptService;

    private final IngredientService ingredientService;

    public IngredientPageController(ReceiptService receiptService, IngredientService ingredientService) {
        this.receiptService = receiptService;
        this.ingredientService = ingredientService;
    }

    @GetMapping({"/ingredient/addingredient/{receiptId}"})
    public String addIngredientPage(@PathVariable("receiptId") long receiptId, Model model) {
        model.addAttribute("receiptDTO", receiptService.findById(receiptId));
        return "addingredient";
    }

    @GetMapping({"/ingredient/deleteingredient/{ingredientId}"})
    public String deleteIngredientPage(@PathVariable("ingredientId") long ingredientId, Model model) {
        model.addAttribute("ingredient", ingredientService.findById(ingredientId));
        return "deleteingredient";
    }

}
