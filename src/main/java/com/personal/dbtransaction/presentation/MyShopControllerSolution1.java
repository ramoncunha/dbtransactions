package com.personal.dbtransaction.presentation;

import com.personal.dbtransaction.domain.BuyProductRequest;
import com.personal.dbtransaction.domain.OutOfStockException;
import com.personal.dbtransaction.infrastructure.model.CustomerEntity;
import com.personal.dbtransaction.infrastructure.model.OrderEntity;
import com.personal.dbtransaction.infrastructure.model.ProductEntity;
import com.personal.dbtransaction.infrastructure.model.StockEntity;
import com.personal.dbtransaction.infrastructure.repository.OrderRepository;
import com.personal.dbtransaction.infrastructure.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/myshop")
@RequiredArgsConstructor
public class MyShopControllerSolution1 {
    private static final Random RANDOM = new Random();

    private final StockRepository stockRepository;
    private final OrderRepository orderRepository;

    @PostMapping("/products/buy")
    public ResponseEntity<OrderEntity> buyProduct(@RequestBody BuyProductRequest request) {
        int quantity = getRandomNumber();

        var customer = CustomerEntity.builder()
                .id(request.getCustomerId())
                .build();
        var product = ProductEntity.builder()
                .id(request.getProductId())
                .build();

        OrderEntity savedOrder = validateStockWithLock(request.getProductId(), quantity, customer, product);

        return new ResponseEntity<>(savedOrder, HttpStatus.OK);
    }

    @Transactional(Transactional.TxType.MANDATORY)
    public OrderEntity validateStockWithLock(Long productId, int quantity, CustomerEntity customer, ProductEntity product) {
        Optional<StockEntity> stockByProductId = stockRepository.findStockByProductId(productId);
        int stock = stockByProductId.orElseThrow().getStock();
        int possibleStock = stock - quantity;

        if (stock < 0 || possibleStock < 0) {
            throw new OutOfStockException("Out of stock");
        }

        stockRepository.decreaseStock(productId, quantity);

        var order = OrderEntity.builder()
                .customer(customer)
                .product(product)
                .quantity(quantity)
                .date(OffsetDateTime.now())
                .build();

        return orderRepository.save(order);
    }

    private int getRandomNumber() {
        return RANDOM.nextInt(10) + 1;
    }
}