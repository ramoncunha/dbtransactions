package com.personal.dbtransaction.application;

import com.personal.dbtransaction.domain.model.CustomerEntity;
import com.personal.dbtransaction.domain.model.OrderEntity;
import com.personal.dbtransaction.domain.model.ProductEntity;
import com.personal.dbtransaction.infrastructure.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOrderService {

    private final OrderRepository orderRepository;

    public OrderEntity create(CustomerEntity customer, ProductEntity product, int quantity) {
        OrderEntity order = OrderEntity.builder()
                .customer(customer)
                .product(product)
                .quantity(quantity)
                .build();
        return orderRepository.save(order);
    }
}
