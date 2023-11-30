package com.service.bearrecipes.dao.impl;

import com.service.bearrecipes.dao.StepInfoRepository;
import com.service.bearrecipes.model.StepInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class StepInfoRepositoryTest {
    @Autowired
    private StepInfoRepository stepInfoRepository;

    @Autowired
    private TestEntityManager em;

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
        var stepInfo = em.find(StepInfo.class, 1);

        assertEquals(1, stepInfo.getId());
        assertEquals("Test Step", stepInfo.getStep());

        stepInfoRepository.deleteById(1L);

        assertNull(em.find(StepInfo.class, 1));
    }
}
