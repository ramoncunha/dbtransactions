package com.personal.dbtransaction.infrastructure.repository;

import com.personal.dbtransaction.infrastructure.model.StockEntity;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StockRepository extends CrudRepository<StockEntity, Long> {

//    FOR UPDATE - Solution 1
//    @Transactional
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<StockEntity> findStockByProductId(Long productId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE stocks SET stock = stock - :quantity WHERE product_id = :productId")
    void decreaseStock(@Param("productId") Long productId, @Param("quantity") int quantity);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE stocks SET stock = stock - :quantity WHERE product_id = :productId" +
            " AND stock > 0")
    int decreaseStockWhereQuantityGreaterThanZero(@Param("productId") Long productId, @Param("quantity") int quantity);
}
