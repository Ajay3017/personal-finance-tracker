package com.project.finance.controller;

import com.project.finance.dto.CategoryBreakdown;
import com.project.finance.dto.Transaction;
import com.project.finance.dto.TransactionSummary;
import com.project.finance.service.impl.TransactionServiceImpl;
import com.project.finance.util.CurrentUserId;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@SecurityRequirement(name = "Bearer Authentication")
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    @GetMapping("/getAllTransactions")
    public List<Transaction> getAllTransactions(){
        return transactionServiceImpl.getAllTransactions();
    }

    @GetMapping("/getTransaction")
    public List<Transaction> getTransaction(@CurrentUserId Long userId){
        log.info("User Id inside resolver: {}", userId);
        return transactionServiceImpl.getTransactionByUser(userId);
    }

    @PostMapping("/saveTransaction")
    public boolean saveTransaction(@RequestBody @Valid Transaction transaction) {
        return transactionServiceImpl.saveTransaction(transaction);
    }

    @PutMapping("/updateTransaction")
    public Transaction updateTransaction(@RequestParam(required = false) @CurrentUserId Long userId, @RequestBody @Valid Transaction transaction) {
        return transactionServiceImpl.updateTransaction(userId, transaction);
    }

    @PostMapping("/deleteTransaction")
    public ResponseEntity<String> deleteTransaction(@CurrentUserId Long userId) {
        transactionServiceImpl.deleteTransaction(userId);
        return ResponseEntity.ok("Deleted Transaction with id: "+ userId);
    }

    @GetMapping("/getMonthlySummary")
    public TransactionSummary getMonthlySummary(@CurrentUserId Long userId, @RequestParam String yearMonth){
        return transactionServiceImpl.getMonthlySummary(userId, yearMonth);
    }

    @GetMapping("/getCategoryBreakdown")
    public List<CategoryBreakdown> getCategoryBreakdown(@CurrentUserId Long userId, @RequestParam String yearMonth){
        return transactionServiceImpl.getCategoryBreakdown(userId, yearMonth);
    }
}
