package com.personal.dbtransaction.repository;

import com.personal.dbtransaction.model.StockEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StockRepository extends CrudRepository<StockEntity, Long> {

    Optional<StockEntity> findStockByProductId(Long productId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE stocks SET stock = stock - :quantity WHERE product_id = :productId")
    void decreaseStock(@Param("productId") Long productId, @Param("quantity") int quantity);
}
