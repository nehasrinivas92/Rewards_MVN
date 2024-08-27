package com.retail.reward.service;

import com.retail.reward.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RewardServiceTest {

    private RewardService rewardService;

    @BeforeEach
    public void setUp() {
        rewardService = new RewardService();
    }

    @Test
    public void testCalculatePoints() {
        assertEquals(90, rewardService.calculatePoints(120));  // 2 points for every dollar over 100, 1 point for every dollar over 50
        assertEquals(40, rewardService.calculatePoints(90));   // 1 point for every dollar over 50
        assertEquals(0, rewardService.calculatePoints(45));    // No points for amount below 50
    }

    @Test
    public void testGetPointsForCustomer() {

        List<Transaction> transactions = Arrays.asList(
                new Transaction(1L, LocalDate.of(2023, 6, 15), 120),
                new Transaction(1L, LocalDate.of(2023, 7, 3), 90),
                new Transaction(1L, LocalDate.of(2023, 7, 22), 75),
                new Transaction(2L, LocalDate.of(2023, 6, 15), 200)
        );

        Map<String, Integer> result = rewardService.getPointsForCustomer(1L, transactions);

        assertEquals(90, result.get("JUNE"));
        assertEquals(65, result.get("JULY"));
        assertEquals(155, result.get("Total"));
    }
}