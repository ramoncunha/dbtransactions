package com.personal.dbtransaction.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderItemEntity> orderItems;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ReviewEntity> reviews;

}
