package com.fsse2401.backend_project_redo02.repository;

import com.fsse2401.backend_project_redo02.data.transaction.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
    Optional<TransactionEntity> findByStatusAndTid(Enum processing, Integer tid);
}
