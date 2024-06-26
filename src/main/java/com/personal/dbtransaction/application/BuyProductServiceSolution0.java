package com.personal.dbtransaction.application;

import com.personal.dbtransaction.domain.BuyProductRequest;
import com.personal.dbtransaction.domain.model.CustomerEntity;
import com.personal.dbtransaction.domain.model.OrderEntity;
import com.personal.dbtransaction.domain.model.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuyProductServiceSolution0 {

    private final CreateOrderService createOrderService;
    private final ProductStockService productStockService;

    public OrderEntity buy(BuyProductRequest request) {
        long productId = request.getProductId();
        int quantity = request.getQuantity();

        var customerMock = CustomerEntity.builder()
                .id(request.getCustomerId())
                .build();
        var productMock = ProductEntity.builder()
                .id(request.getProductId())
                .build();

        productStockService.validateAndDecreaseSolution0(productId, quantity);

        return createOrderService.create(customerMock, productMock, quantity);
    }
}
