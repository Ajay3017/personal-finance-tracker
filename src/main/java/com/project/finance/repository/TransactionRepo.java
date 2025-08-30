package com.project.finance.repository;

import com.project.finance.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findByUserEntity_UserId(@Param("userId") Long userId);
}
