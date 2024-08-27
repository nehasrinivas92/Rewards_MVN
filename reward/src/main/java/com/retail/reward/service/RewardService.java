package com.retail.reward.service;

import com.retail.reward.model.Transaction;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardService {

    private static final Logger logger = LoggerFactory.getLogger(RewardService.class);

    public int calculatePoints(double amount) {
        logger.info("Calculating points for amount: {}", amount);
        int points = 0;
        if (amount > 100) {
            points += (amount - 100) * 2;
            amount = 100;
        }
        if (amount > 50) {
            points += (amount - 50);
        }
        return points;
    }

    public Map<String, Integer> getPointsForCustomer(Long customerId, List<Transaction> transactions) {
        logger.info("Processing transactions for customer ID: {}", customerId);
        logger.info("Transactions received: {}", transactions);

        Map<String, Integer> monthlyPoints = new HashMap<>();
        try {
            Map<String, List<Transaction>> transactionsByMonth = transactions.stream()
                    .filter(t -> t.getCustomerId().equals(customerId))
                    .collect(Collectors.groupingBy(t -> t.getDate().getMonth().toString()));

            logger.info("Filtered transactions by month: {}", transactionsByMonth);

            transactionsByMonth.forEach((month, transactionList) -> {
                int totalPoints = transactionList.stream()
                        .mapToInt(t -> calculatePoints(t.getAmount()))
                        .sum();
                logger.info("Total points for month {}: {}", month, totalPoints);
                monthlyPoints.put(month, totalPoints);
            });

            int totalPoints = monthlyPoints.values().stream().mapToInt(Integer::intValue).sum();
            monthlyPoints.put("Total", totalPoints);

            logger.info("Total points calculated: {}", totalPoints);

        } catch (Exception e) {
            logger.error("Error calculating points for customer ID: {}", customerId, e);
        }

        return monthlyPoints;
    }
}