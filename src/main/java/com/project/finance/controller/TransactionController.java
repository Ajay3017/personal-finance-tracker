package com.project.finance.controller;

import com.project.finance.dto.Transaction;
import com.project.finance.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    @GetMapping("/getTransactions")
    public List<Transaction> getAllTransactions(){
        return transactionServiceImpl.getAllTransactions();
    }
}
