package com.project.finance.controller;

import com.project.finance.dto.CategoryBreakdown;
import com.project.finance.dto.Transaction;
import com.project.finance.dto.TransactionSummary;
import com.project.finance.service.impl.TransactionServiceImpl;
import com.project.finance.util.CurrentUserId;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class TransactionController {

    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    @GetMapping("/getAllTransactions")
    public List<Transaction> getAllTransactions(){
        return transactionServiceImpl.getAllTransactions();
    }

    @GetMapping("/getTransactionByUser")
    public List<Transaction> getTransactionByUserId(@Parameter(hidden = true) @CurrentUserId Long userId){
        return transactionServiceImpl.getTransactionByUser(userId);
    }

    @PostMapping("/saveTransaction")
    public boolean saveTransaction(@RequestBody @Valid Transaction transaction,
                                   @Parameter(hidden = true) @CurrentUserId Long userId) {
        transaction.setUserId(userId);
        return transactionServiceImpl.saveTransaction(transaction);
    }

    @PutMapping("/updateTransaction")
    public Transaction updateTransaction(@RequestParam Long transId,
                                         @Parameter(hidden = true) @CurrentUserId Long userId,
                                         @RequestBody @Valid Transaction transaction) {
        transaction.setUserId(userId);
        return transactionServiceImpl.updateTransaction(transId, transaction);
    }

    @PostMapping("/deleteTransaction")
    public ResponseEntity<String> deleteTransaction(@RequestParam Long transId) {
        transactionServiceImpl.deleteTransaction(transId);
        return ResponseEntity.ok("Deleted Transaction with id: "+ transId);
    }

    @GetMapping("/getMonthlySummary")
    public TransactionSummary getMonthlySummary(@Parameter(hidden = true) @CurrentUserId Long userId,
                                                @RequestParam String yearMonth){
        return transactionServiceImpl.getMonthlySummary(userId, yearMonth);
    }

    @GetMapping("/getCategoryBreakdown")
    public List<CategoryBreakdown> getCategoryBreakdown(@Parameter(hidden = true) @CurrentUserId Long userId,
                                                        @RequestParam String yearMonth){
        return transactionServiceImpl.getCategoryBreakdown(userId, yearMonth);
    }
}
