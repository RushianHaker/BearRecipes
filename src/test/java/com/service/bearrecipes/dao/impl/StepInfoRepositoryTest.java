package com.service.bearrecipes.dao.impl;

import com.service.bearrecipes.config.DbTestcontainersConfig;
import com.service.bearrecipes.dao.StepInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("junit")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {DbTestcontainersConfig.class})
class StepInfoRepositoryTest {
    @Autowired
    private StepInfoRepository stepInfoRepository;

    @Test
    void findById() {
        var stepInfo = stepInfoRepository.findById(1L);

        assertTrue(stepInfo.isPresent());

        var presentStepInfo = stepInfo.get();
        assertEquals(1, presentStepInfo.getId());
        assertEquals("Test Step", presentStepInfo.getStep());
    }

    @Test
    @Rollback
    void deleteById() {
        var stepInfo = stepInfoRepository.findById(1L).orElseThrow();

        assertEquals(1, stepInfo.getId());
        assertEquals("Test Step", stepInfo.getStep());

        stepInfoRepository.deleteById(1L);

        assertFalse(stepInfoRepository.findById(1L).isPresent());
    }
}
