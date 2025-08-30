package com.project.finance.service;

import com.project.finance.dto.Transaction;

import java.util.List;

public interface TransactionService {

    public List<Transaction> getAllTransactions();

    public List<Transaction> getTransactionByUser(Long userId);

    public boolean saveTransaction(Transaction transaction);
}
