package com.retail.reward.controller;

import com.retail.reward.service.RewardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RewardControllerTest {

    @InjectMocks
    private RewardController rewardController;

    @Spy
    private RewardService rewardService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCustomerRewards() {
        ResponseEntity<Map<String, Integer>> response = rewardController.getCustomerRewards(1L);
        assertEquals(155, response.getBody().get("Total"));
        assertEquals(90, response.getBody().get("JUNE"));
        assertEquals(65, response.getBody().get("JULY"));
    }

    @Test
    public void testGetWithNoCustomerRewards() {
        ResponseEntity<Map<String, Integer>> response = rewardController.getCustomerRewards(4L);
        assertEquals(0, response.getBody().get("Total"));
    }
}
