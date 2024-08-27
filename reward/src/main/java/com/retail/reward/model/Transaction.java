package com.retail.reward.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Transaction {
    private Long customerId;
    private LocalDate date;
    private double amount;
}

