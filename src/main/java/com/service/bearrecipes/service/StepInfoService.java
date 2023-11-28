package com.service.bearrecipes.service;


import com.service.bearrecipes.dto.StepInfoDTO;
import com.service.bearrecipes.model.StepInfo;

import java.util.List;

public interface StepInfoService {
    StepInfoDTO findById(long stepInfoId);

    List<StepInfo> findAll();

    StepInfo saveReceiptsStepInfo(StepInfo stepInfo);

    List<StepInfo> saveAllSteps(List<StepInfo> steps);

    List<StepInfo> saveAllStepsDTO(List<StepInfoDTO> steps);

    void deleteStepInfoById(long id);
}
