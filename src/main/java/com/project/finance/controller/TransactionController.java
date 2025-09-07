package com.project.finance.controller;

import com.project.finance.dto.Transaction;
import com.project.finance.service.impl.TransactionServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping
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

    @PostMapping("/saveTransaction")
    public boolean saveTransaction(@RequestBody @Valid Transaction transaction) {
        return transactionServiceImpl.saveTransaction(transaction);
    }

    @PutMapping("/updateTransaction")
    public Transaction updateTransaction(@RequestParam Long id, @RequestBody @Valid Transaction transaction) {
        return transactionServiceImpl.updateTransaction(id, transaction);
    }

    @PostMapping("/deleteTransaction")
    public ResponseEntity<String> deleteTransaction(@RequestParam Long id) {
        transactionServiceImpl.deleteTransaction(id);
        return ResponseEntity.ok("Deleted Transaction with id: "+ id);
    }

    @GetMapping("/getMonthlyBalance")
    public double getMonthlyBalance(@RequestParam Long userId, @RequestParam YearMonth month){
        return transactionServiceImpl.getMonthlyBalance(userId, month);
    }
}
