package com.project.finance.service;

import com.project.finance.dto.Transaction;

import java.util.List;

public interface TransactionService {

    public List<Transaction> getAllTransactions();

    //To DO: Add save & getTransactionByUserId
}
