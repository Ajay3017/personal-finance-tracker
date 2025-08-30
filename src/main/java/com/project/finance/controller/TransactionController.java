package com.project.finance.controller;

import com.project.finance.dto.Transaction;
import com.project.finance.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    @GetMapping("/getAllTransactions")
    public List<Transaction> getAllTransactions(){
        return transactionServiceImpl.getAllTransactions();
    }

    @GetMapping("/getTransaction/{userId}")
    public List<Transaction> getTransaction(@PathVariable("userId") Long userId){
        return transactionServiceImpl.getTransactionByUser(userId);
    }

    @PutMapping("/saveTransaction")
    public boolean saveTransaction(@RequestBody Transaction transaction) {
        return transactionServiceImpl.saveTransaction(transaction);
    }
}
