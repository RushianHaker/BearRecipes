package com.service.bearrecipes.controller.page;

import com.service.bearrecipes.service.AuthorService;
import com.service.bearrecipes.service.CountryService;
import com.service.bearrecipes.service.ReceiptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ReceiptPageController {

    private final ReceiptService receiptService;

    private final AuthorService authorService;

    private final CountryService countryService;

    public ReceiptPageController(ReceiptService receiptService, AuthorService authorService, CountryService countryService) {
        this.receiptService = receiptService;
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @GetMapping({"/receipt"})
    public String getAllReceipts() {
        return "receiptlist";
    }

    @GetMapping({"/receipt/inforeceipt/{receiptId}"})
    public String infoReceiptPage(@PathVariable("receiptId") long receiptId, Model model) {
        model.addAttribute("receipt", receiptService.findById(receiptId));
        return "inforeceipt";
    }

    @GetMapping({"/receipt/addreceipt"})
    public String addReceiptPage(Model model) {
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("countries", countryService.findAll());
        return "addreceipt";
    }

    @GetMapping({"/receipt/editreceipt/{receiptId}"})
    public String editReceiptPage(@PathVariable("receiptId") long receiptId, Model model) {
        model.addAttribute("receipt", receiptService.findById(receiptId));
        return "editreceipt";
    }

    @GetMapping({"/receipt/delreceipt/{receiptId}"})
    public String delReceiptPage(@PathVariable("receiptId") long receiptId, Model model) {
        model.addAttribute("receipt", receiptService.findById(receiptId));
        return "delreceipt";
    }
}
