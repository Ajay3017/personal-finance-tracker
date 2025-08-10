package com.project.finance.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "USER")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;

    private String name;

    private String email;

    private String password;

    private Timestamp createdAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userEntity", orphanRemoval = true)
    private List<TransactionEntity> transactionEntityList;
}
