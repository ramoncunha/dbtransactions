package com.personal.dbtransaction.application;

import com.personal.dbtransaction.domain.OutOfStockException;
import com.personal.dbtransaction.domain.model.StockEntity;
import com.personal.dbtransaction.infrastructure.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductStockService {

    private final StockRepository stockRepository;

    @Transactional
    public void validateAndDecrease(long productId, int quantity, long delay) {
        Optional<StockEntity> stockByProductId = stockRepository.findStockByProductIdWithLock(productId);

        int stock = stockByProductId.orElseThrow().getStock();
        int futureStock = stock - quantity;

        if (stock < 0 || futureStock < 0) {
            throw new OutOfStockException("Out of stock");
        }

//        try {
//            Thread.sleep(Duration.ofSeconds(delay).toMillis());
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        stockRepository.decreaseStock(productId, quantity);
    }
}
