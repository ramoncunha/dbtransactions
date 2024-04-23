package com.personal.dbtransaction.infrastructure.repository;

import com.personal.dbtransaction.domain.model.StockEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {

    Optional<StockEntity> findStockByProductId(Long productId);

    @Query(value = "SELECT * FROM stocks s WHERE s.product_id = ?1 FOR UPDATE", nativeQuery = true)
    Optional<StockEntity> findStockByProductIdWithLock(Long productId);

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
