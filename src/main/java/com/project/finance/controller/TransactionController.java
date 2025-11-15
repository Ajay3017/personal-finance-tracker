package com.project.finance.controller;

import com.project.finance.dto.CategoryBreakdown;
import com.project.finance.dto.Transaction;
import com.project.finance.dto.TransactionSummary;
import com.project.finance.service.impl.TransactionServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@SecurityRequirement(name = "Bearer Authentication")
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

    @GetMapping("/getMonthlySummary")
    public TransactionSummary getMonthlySummary(@RequestParam Long userId, @RequestParam String yearMonth){
        return transactionServiceImpl.getMonthlySummary(userId, yearMonth);
    }

    @GetMapping("/getCategoryBreakdown")
    public List<CategoryBreakdown> getCategoryBreakdown(@RequestParam Long userId, @RequestParam String yearMonth){
        return transactionServiceImpl.getCategoryBreakdown(userId, yearMonth);
    }
}
