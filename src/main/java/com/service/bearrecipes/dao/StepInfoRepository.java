package com.service.bearrecipes.dao;

import com.service.bearrecipes.model.StepInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StepInfoRepository extends JpaRepository<StepInfo, Long> {
}
