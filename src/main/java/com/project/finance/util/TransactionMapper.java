package com.project.finance.util;

import com.project.finance.dto.Transaction;
import com.project.finance.entity.TransactionEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(source = "userEntity.userId", target = "userId")
    Transaction getTransaction(TransactionEntity transactionEntity);

    @InheritInverseConfiguration
    TransactionEntity getTransactionEntity (Transaction transaction);

    @Mapping(target = "transId", ignore = true)
    @Mapping(target = "userEntity", ignore = true)
    void UpdateToEntity(Transaction dto, @MappingTarget TransactionEntity entity);
}
