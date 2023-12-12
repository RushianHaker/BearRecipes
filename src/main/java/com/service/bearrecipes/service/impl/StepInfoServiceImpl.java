package com.service.bearrecipes.service.impl;

import com.service.bearrecipes.dao.StepInfoRepository;
import com.service.bearrecipes.dto.StepInfoDTO;
import com.service.bearrecipes.exception.ReceiptServiceException;
import com.service.bearrecipes.model.StepInfo;
import com.service.bearrecipes.service.StepInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StepInfoServiceImpl implements StepInfoService {

    private final StepInfoRepository stepInfoRepository;

    public StepInfoServiceImpl(StepInfoRepository stepInfoRepository) {
        this.stepInfoRepository = stepInfoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public StepInfoDTO findById(long stepInfoId) {
        return StepInfoDTO.toDto(stepInfoRepository.findById(stepInfoId)
                .orElseThrow(() -> new ReceiptServiceException("StepInfo not found!")));
    }

    @Override
    @Transactional(readOnly = true)
    public List<StepInfo> findAll() {
        return stepInfoRepository.findAll();
    }

    @Override
    @Transactional
    public StepInfo saveReceiptsStepInfo(StepInfo stepInfo) {
        var receipt = stepInfo.getReceipt();

        if (receipt.getSteps() == null) {
            receipt.setSteps(new ArrayList<>());
            stepInfo.setReceipt(receipt);
        }

        return stepInfoRepository.save(stepInfo);
    }

    @Override
    @Transactional
    public List<StepInfo> saveAllSteps(List<StepInfo> steps) {
        steps.stream()
                .filter(stepInfo -> stepInfo.getReceipt().getSteps() == null)
                .forEach(stepInfo -> {
                    var receipt = stepInfo.getReceipt();
                    receipt.setSteps(new ArrayList<>());
                    stepInfo.setReceipt(receipt);
                });

        return stepInfoRepository.saveAll(steps);
    }

    @Override
    @Transactional
    public List<StepInfo> saveAllStepsDTO(List<StepInfoDTO> steps) {
        var resultList = new ArrayList<StepInfo>();

        steps.forEach(stepDTO -> resultList.add(new StepInfo(stepDTO.getStep(),
                stepDTO.getImage(), stepDTO.getReceiptDTO().toDomainObject())));

        return saveAllSteps(resultList);
    }

    @Override
    public void deleteStepInfoById(long stepInfoId) {
        stepInfoRepository.deleteById(stepInfoId);
    }
}
