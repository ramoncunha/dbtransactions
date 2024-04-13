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

@RestController
@RequestMapping("/api/myshop")
@RequiredArgsConstructor
public class MyShopController {

    private final StockRepository stockRepository;
    private final OrderRepository orderRepository;

    @PostMapping("/products/buy")
    public ResponseEntity<OrderEntity> buyProduct(@RequestBody BuyProductRequest request) {
        var customer = CustomerEntity.builder()
                .id(request.getCustomerId())
                .build();

        var product = ProductEntity.builder()
                .id(request.getProductId())
                .build();

        var order = OrderEntity.builder()
                .customer(customer)
                .product(product)
                .quantity(request.getQuantity())
                .date(OffsetDateTime.now())
                .build();

        Optional<StockEntity> stockByProductId = stockRepository.findStockByProductId(product.getId());

        int stock = stockByProductId.orElseThrow().getStock();
        int possibleStock = stock - request.getQuantity();

        if (stock < 0 || possibleStock < 0) {
            throw new RuntimeException("Out of stock");
        }

        stockRepository.decreaseStock(product.getId(), request.getQuantity());
        OrderEntity savedOrder = orderRepository.save(order);

        return new ResponseEntity<>(savedOrder, HttpStatus.OK);
    }
}
