package com.personal.dbtransaction.repository;

import com.personal.dbtransaction.model.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
}
