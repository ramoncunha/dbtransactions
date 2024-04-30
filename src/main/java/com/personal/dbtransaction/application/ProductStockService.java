package com.personal.dbtransaction.application;

import com.personal.dbtransaction.domain.OutOfStockException;
import com.personal.dbtransaction.domain.model.StockEntity;
import com.personal.dbtransaction.infrastructure.repository.StockRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductStockService {

    private final StockRepository stockRepository;
    private final EntityManager entityManager;

    public void validateAndDecreaseSolution0(long productId, int quantity) {
        Optional<StockEntity> stockByProductId = stockRepository.findStockByProductId(productId);

        int stock = stockByProductId.orElseThrow().getStock();
        int possibleStock = stock - quantity;

        if (stock < 0 || possibleStock < 0) {
            throw new OutOfStockException("Out of stock");
        }

        stockRepository.decreaseStock(productId, quantity);
    }

    public void validateAndDecreaseSolution1(long productId, int quantity, long delay) {
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

    public void validateSolution3(long productId, int quantity) {
        Optional<StockEntity> stockByProductId = stockRepository.findStockByProductId(productId);

        int stock = stockByProductId.orElseThrow().getStock();
        int possibleStock = stock - quantity;

        if (stock < 0 || possibleStock < 0) {
            throw new OutOfStockException("Out of stock");
        }
    }

    public void acquireLockAndDecreaseSolution2(long productId, int quantity) {
        Query nativeQuery = entityManager.createNativeQuery("select pg_advisory_xact_lock(:lockId)");
        nativeQuery.setParameter("lockId", productId);
        nativeQuery.getSingleResult();

        Optional<StockEntity> stockByProductId = stockRepository.findStockByProductId(productId);

        int stock = stockByProductId.orElseThrow().getStock();
        int possibleStock = stock - quantity;

        if (stock < 0 || possibleStock < 0) {
            throw new OutOfStockException("Out of stock");
        }

        stockRepository.decreaseStock(productId, quantity);
    }

    public void decreaseSolution3(long productId, int quantity) {
        int changedRows = stockRepository.decreaseStockWhereQuantityGreaterThanZero(productId, quantity);
        if (changedRows == 0) {
            throw new OutOfStockException("Out of stock");
        }
    }
}
