package com.service.bearrecipes.controller.rest;

import com.service.bearrecipes.dto.StepInfoDTO;
import com.service.bearrecipes.model.StepInfo;
import com.service.bearrecipes.service.StepInfoService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StepInfoRestController {
    private final StepInfoService stepInfoService;

    public StepInfoRestController(StepInfoService stepInfoService) {
        this.stepInfoService = stepInfoService;
    }

    @PostMapping("/api/step")
    public void addStepInfo(@RequestBody @NotNull StepInfoDTO stepInfoDTO) {
        stepInfoService.saveReceiptsStepInfo(new StepInfo(stepInfoDTO.getStep(),
                stepInfoDTO.getImage(), stepInfoDTO.getReceiptDTO().toDomainObject()));
    }

    @PostMapping("/api/step/save_all")
    public void addListSteps(@RequestBody @NotNull List<StepInfoDTO> steps) {
        stepInfoService.saveAllStepsDTO(steps);
    }

    @DeleteMapping("/api/step/{stepId}")
    public void deleteStepInfoById(@PathVariable("stepId") long stepId) {
        stepInfoService.deleteStepInfoById(stepId);
    }
}
