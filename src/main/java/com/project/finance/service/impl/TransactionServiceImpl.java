package com.project.finance.service.impl;

import com.project.finance.dto.CategoryBreakdown;
import com.project.finance.dto.Transaction;
import com.project.finance.dto.TransactionSummary;
import com.project.finance.dto.TransactionType;
import com.project.finance.entity.TransactionEntity;
import com.project.finance.repository.TransactionRepo;
import com.project.finance.service.TransactionService;
import com.project.finance.util.TransactionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public List<Transaction> getAllTransactions() {
        log.info("In getAllTransactions");
        List<TransactionEntity> transactionEntityList = transactionRepo.findAll();

        return transactionEntityList.stream()
                .map(transactionMapper::getTransaction)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getTransactionByUser(Long userId) {
        log.info("In getTransactionByUser");
        List<TransactionEntity> transactionEntities = transactionRepo.findByUserEntity_UserId(userId);

        return transactionEntities.stream()
                .map(transactionMapper::getTransaction)
                .collect(Collectors.toList());
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
    public TransactionSummary getMonthlySummary(Long userId, String yearMonthStr) {

        YearMonth yearMonth = YearMonth.parse(yearMonthStr);

        LocalDate start = yearMonth.atDay(1);
        LocalDate end = yearMonth.atEndOfMonth();

        Timestamp  startTime = Timestamp.valueOf(start.atStartOfDay());
        Timestamp endTime = Timestamp.valueOf(end.atTime(LocalTime.MAX));

        List<TransactionEntity> transactionEntityList = transactionRepo.findByUserEntity_UserIdAndDateBetween(userId, startTime, endTime);

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

        return new TransactionSummary(income, expense, savings,income-(savings+expense));
    }

    @Override
    public List<CategoryBreakdown> getCategoryBreakdown(Long userId, String yearMonthStr) {

        YearMonth yearMonth = YearMonth.parse(yearMonthStr);

        LocalDate start = yearMonth.atDay(1);
        LocalDate end = yearMonth.atEndOfMonth();

        Timestamp  startTime = Timestamp.valueOf(start.atStartOfDay());
        Timestamp endTime = Timestamp.valueOf(end.atTime(LocalTime.MAX));

        return transactionRepo.findByCategory(userId, startTime, endTime);
    }


}
