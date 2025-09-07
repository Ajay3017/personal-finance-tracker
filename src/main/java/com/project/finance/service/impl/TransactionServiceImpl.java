package com.project.finance.service.impl;

import com.project.finance.dto.Transaction;
import com.project.finance.dto.TransactionType;
import com.project.finance.entity.TransactionEntity;
import com.project.finance.repository.TransactionRepo;
import com.project.finance.service.TransactionService;
import com.project.finance.util.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public List<Transaction> getAllTransactions() {

        List<TransactionEntity> transactionEntityList = transactionRepo.findAll();
        List<Transaction> transactionList = new ArrayList<>();

        transactionEntityList.forEach((transaction) -> {
            transactionList.add(transactionMapper.getTransaction(transaction));
        });
        return transactionList;
    }

    @Override
    public List<Transaction> getTransactionByUser(Long userId) {
        List<TransactionEntity> transactionEntities = transactionRepo.findByUserEntity_UserId(userId);

        List<Transaction> transactions = new ArrayList<Transaction>();
        transactionEntities.forEach(transactionEntity -> {
            transactions.add(transactionMapper.getTransaction(transactionEntity));
        });
        return transactions;
    }

    @Override
    public boolean saveTransaction(Transaction transaction) {

        TransactionEntity transactionEntity = transactionRepo.save(transactionMapper.getTransactionEntity(transaction));
        return transactionEntity.getTransId() != null;
    }

    @Override
    public Transaction updateTransaction(Long id, Transaction transaction) {

        TransactionEntity existingEntity = transactionRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Transaction Not found"));

        transactionMapper.UpdateToEntity(transaction, existingEntity);
        TransactionEntity savedEntity = transactionRepo.save(existingEntity);

        return transactionMapper.getTransaction(savedEntity);
    }

    @Override
    public void deleteTransaction(Long transId) {
        TransactionEntity existingEntity = transactionRepo.findById(transId).orElseThrow(() ->
                new RuntimeException("Transaction Not found"));
        transactionRepo.deleteById(transId);

    }

    @Override
    public double getMonthlyBalance(Long userId, YearMonth month) {

        List<TransactionEntity> transactionEntityList = transactionRepo.findByUserEntity_UserId(userId);


//        List<TransactionEntity> currentMonthList = transactionEntityList.stream().
//                filter(entity -> {
//                    (entity.getDate().toLocalDateTime()).
//                });

        double income = transactionEntityList.stream().
                filter(entity-> entity.getTransactionType().equals(TransactionType.INCOME))
                .mapToDouble(TransactionEntity::getAmount)
                .sum();

        double savings = transactionEntityList.stream().
                filter(entity-> entity.getTransactionType().equals(TransactionType.SAVING))
                .mapToDouble(TransactionEntity::getAmount)
                .sum();

        double expense = transactionEntityList.stream().
                filter(entity-> entity.getTransactionType().equals(TransactionType.EXPENSE))
                .mapToDouble(TransactionEntity::getAmount)
                .sum();

        return income-(savings+expense);
    }

}
