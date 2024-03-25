package com.fsse2401.backend_project_redo02.repository;

import com.fsse2401.backend_project_redo02.data.transactionProduct.entity.TransactionProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionProductRepository extends CrudRepository<TransactionProductEntity, Integer> {
}
