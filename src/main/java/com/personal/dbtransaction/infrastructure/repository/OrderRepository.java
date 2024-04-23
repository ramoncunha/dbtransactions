package com.personal.dbtransaction.infrastructure.repository;

import com.personal.dbtransaction.domain.model.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
}
