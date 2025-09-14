package com.project.finance.service;

import com.project.finance.dto.CategoryBreakdown;
import com.project.finance.dto.Transaction;
import com.project.finance.dto.TransactionSummary;

import java.util.List;

public interface TransactionService {

    public List<Transaction> getAllTransactions();

    public List<Transaction> getTransactionByUser(Long userId);

    public boolean saveTransaction(Transaction transaction);

    public Transaction updateTransaction(Long id, Transaction transaction);

    public void deleteTransaction(Long transId);

    public TransactionSummary getMonthlySummary(Long userId, String yearMonth);

    public List<CategoryBreakdown> getCategoryBreakdown(Long userId, String yearMonth);

}
