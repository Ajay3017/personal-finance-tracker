package com.project.finance.service.impl;

import com.project.finance.dto.Transaction;
import com.project.finance.entity.TransactionEntity;
import com.project.finance.repository.TransactionRepo;
import com.project.finance.service.TransactionService;
import com.project.finance.util.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
