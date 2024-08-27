package com.retail.reward.controller;

import com.retail.reward.model.Transaction;
import com.retail.reward.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    private List<Transaction> transactions = Arrays.asList(
            new Transaction(1L, LocalDate.of(2023, 6, 15), 120),
            new Transaction(1L, LocalDate.of(2023, 7, 3), 90),
            new Transaction(1L, LocalDate.of(2023, 7, 22), 75),
            new Transaction(2L, LocalDate.of(2023, 6, 22), 45),
            new Transaction(2L, LocalDate.of(2023, 8, 5), 200)
    );

    @GetMapping("/{customerId}")
    public ResponseEntity<Map<String, Integer>> getCustomerRewards(@PathVariable Long customerId) {
        Map<String, Integer> rewards = rewardService.getPointsForCustomer(customerId, transactions);
        return ResponseEntity.ok(rewards);
    }
}

