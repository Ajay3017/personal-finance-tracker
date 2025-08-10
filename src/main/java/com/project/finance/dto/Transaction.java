package com.project.finance.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Transaction {

    private Long transId;

    private Long userId;

    private String title;

    private Double amount;

    private TransactionType transactionType;

    private String category;

    private Timestamp date;

    private String note;
}
