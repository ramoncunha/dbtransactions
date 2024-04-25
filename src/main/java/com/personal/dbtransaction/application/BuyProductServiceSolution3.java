package com.personal.dbtransaction.application;

import com.personal.dbtransaction.domain.BuyProductRequest;
import com.personal.dbtransaction.domain.model.CustomerEntity;
import com.personal.dbtransaction.domain.model.OrderEntity;
import com.personal.dbtransaction.domain.model.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuyProductServiceSolution3 {

    private final CreateOrderService createOrderService;
    private final ProductStockService productStockService;

    public OrderEntity buy(BuyProductRequest request) {
        long productId = request.getProductId();
        int quantity = request.getQuantity();

        var customerMock = CustomerEntity.builder()
                .id(request.getCustomerId())
                .build();
        var productMock = ProductEntity.builder()
                .id(productId)
                .build();

        productStockService.validateSolution2And3(productId, quantity);
        productStockService.decreaseSolution3(productId, quantity);

        return createOrderService.create(customerMock, productMock, quantity);
    }
}
