package com.personal.dbtransaction.repository;

import com.personal.dbtransaction.model.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
}
