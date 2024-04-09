package com.personal.dbtransaction.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Builder
@Table(name = "reviews")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    private int rating;

    private String comment;

    private OffsetDateTime date;
}
