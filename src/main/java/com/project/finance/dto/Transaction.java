package com.project.finance.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Transaction {

    private Long transId;

    @NotNull
    private Long userId;

    @NotEmpty(message = "Title cannot be empty")
    @Size(max=50, message = "Title must not exceed 50 characters")
    private String title;

    @NotNull(message="Amount cannot be null")
    @Min(0)
    private Double amount;

    @NotNull
    private TransactionType transactionType;

    @NotEmpty(message = "Category cannot be empty")
    @Size(max=50, message = "Category must not exceed 50 characters")
    private String category;

    @PastOrPresent(message = "Transaction date cannot be in future")
    private Timestamp date;

    @Size(max=200, message = "Note must not exceed 200 characters")
    private String note;
}
