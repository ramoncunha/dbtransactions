package com.personal.dbtransaction.repository;

import com.personal.dbtransaction.model.OrderItemEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItemEntity, Long> {
}
