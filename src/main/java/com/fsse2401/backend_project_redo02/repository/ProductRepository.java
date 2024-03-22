package com.fsse2401.backend_project_redo02.repository;

import com.fsse2401.backend_project_redo02.data.product.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
}
