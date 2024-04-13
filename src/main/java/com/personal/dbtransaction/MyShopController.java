package com.personal.dbtransaction;

import com.personal.dbtransaction.model.CustomerEntity;
import com.personal.dbtransaction.model.OrderEntity;
import com.personal.dbtransaction.model.ProductEntity;
import com.personal.dbtransaction.model.StockEntity;
import com.personal.dbtransaction.repository.OrderRepository;
import com.personal.dbtransaction.repository.StockRepository;
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
public class MyShopController {
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

        validateStock(product.getId(), quantity);

        var order = OrderEntity.builder()
                .customer(customer)
                .product(product)
                .quantity(quantity)
                .date(OffsetDateTime.now())
                .build();

        stockRepository.decreaseStock(product.getId(), quantity);
        OrderEntity savedOrder = orderRepository.save(order);

        return new ResponseEntity<>(savedOrder, HttpStatus.OK);
    }

    private int getRandomNumber() {
        return RANDOM.nextInt(10) + 1;
    }

    private void validateStock(Long productId, int quantity) {
        Optional<StockEntity> stockByProductId = stockRepository.findStockByProductId(productId);

        int stock = stockByProductId.orElseThrow().getStock();
        int possibleStock = stock - quantity;

        if (stock < 0 || possibleStock < 0) {
            throw new OutOfStockException("Out of stock");
        }
    }
}
