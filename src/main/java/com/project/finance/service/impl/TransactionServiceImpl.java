package com.project.finance.service.impl;

import com.project.finance.dto.Transaction;
import com.project.finance.entity.TransactionEntity;
import com.project.finance.repository.TransactionRepo;
import com.project.finance.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    @Override
    public List<Transaction> getAllTransactions() {

        List<TransactionEntity> transactionEntityList = transactionRepo.findAll();
        List<Transaction> transactionList = new ArrayList<>();

        //To do: reduce manual data setup
        transactionEntityList.forEach((transaction) ->
        {
            Transaction transaction1 = new Transaction();
            transaction1.setTransId(transaction.getTransId());
            transaction1.setUserId(transaction.getUserEntity().getUserId());
            transaction1.setTitle(transaction.getTitle());
            transaction1.setAmount(transaction.getAmount());
            transaction1.setTransactionType(transaction.getTransactionType());
            transaction1.setCategory(transaction.getCategory());
            transaction1.setDate(transaction.getDate());
            transaction1.setNote(transaction.getNote());

            transactionList.add(transaction1);

        });
        return transactionList;
    }

}
