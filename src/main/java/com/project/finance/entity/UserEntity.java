package com.project.finance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userEntity", orphanRemoval = true)
    private List<TransactionEntity> transactionEntityList;

    public UserEntity(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @PrePersist
    void addCreatedDate() {
        if(this.createdAt == null){
            this.createdAt = new Timestamp(System.currentTimeMillis());
        }
    }
}
