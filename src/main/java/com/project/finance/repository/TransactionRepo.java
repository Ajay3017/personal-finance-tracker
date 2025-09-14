package com.project.finance.repository;

import com.project.finance.dto.CategoryBreakdown;
import com.project.finance.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findByUserEntity_UserId(@Param("userId") Long userId);

    List<TransactionEntity> findByUserEntity_UserIdAndDateBetween (Long userId, Timestamp start, Timestamp end);

    @Query("SELECT new com.project.finance.dto.CategoryBreakdown(category, SUM(t.amount)) FROM TransactionEntity t where " +
            "t.userEntity.userId = :userId AND t.date BETWEEN :start AND :end " +
            "GROUP BY t.category")
    List<CategoryBreakdown> findByCategory(@Param("userId") Long userId,
                                           @Param("start") Timestamp start,
                                           @Param("end") Timestamp end);

}
