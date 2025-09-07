package com.project.finance.entity;

import com.project.finance.dto.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;


@Entity
@Table(name = "FINANCE_TRANSACTION")
@Data
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transId")
    private Long transId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name="finance_type", nullable = false)
    private TransactionType transactionType;

    @Column(nullable = false)
    private String category;    // Food, Rent, etc.

    @Column(nullable = false)
    private Timestamp date;

    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity userEntity;
}
