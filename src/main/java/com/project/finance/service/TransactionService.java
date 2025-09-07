package com.project.finance.service;

import com.project.finance.dto.Transaction;

import java.time.Year;
import java.time.YearMonth;
import java.util.List;

public interface TransactionService {

    public List<Transaction> getAllTransactions();

    public List<Transaction> getTransactionByUser(Long userId);

    public boolean saveTransaction(Transaction transaction);

    public Transaction updateTransaction(Long id, Transaction transaction);

    public void deleteTransaction(Long transId);

    public double getMonthlyBalance(Long userId, YearMonth month);
}
