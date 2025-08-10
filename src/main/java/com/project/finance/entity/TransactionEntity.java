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

    private String title;

    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name="finance_type")
    private TransactionType transactionType;

    private String category;

    private Timestamp date;

    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity userEntity;
}
