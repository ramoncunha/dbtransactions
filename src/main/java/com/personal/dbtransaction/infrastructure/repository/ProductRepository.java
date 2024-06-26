package com.personal.dbtransaction.infrastructure.repository;

import com.personal.dbtransaction.domain.model.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
}
