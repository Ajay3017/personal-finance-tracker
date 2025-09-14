package com.project.finance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionSummary {

    private double totalIncome;

    private double totalExpense;

    private double balance;
}
