package com.service.bearrecipes.controller.page;

import com.service.bearrecipes.service.ReceiptService;
import com.service.bearrecipes.service.StepInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StepInfoPageController {
    private final ReceiptService receiptService;
    private final StepInfoService stepInfoService;

    public StepInfoPageController(ReceiptService receiptService, StepInfoService stepInfoService) {
        this.receiptService = receiptService;
        this.stepInfoService = stepInfoService;
    }

    @GetMapping({"/stepinfo/addstep/{receiptId}"})
    public String addStepInfoPage(@PathVariable("receiptId") long receiptId, Model model) {
        model.addAttribute("receiptDTO", receiptService.findById(receiptId));
        return "addstep";
    }

    @GetMapping({"/stepinfo/deletestep/{stepInfoId}"})
    public String deleteStepInfoPage(@PathVariable("stepInfoId") long stepInfoId, Model model) {
        model.addAttribute("stepinfo", stepInfoService.findById(stepInfoId));
        return "deletestep";
    }

}
