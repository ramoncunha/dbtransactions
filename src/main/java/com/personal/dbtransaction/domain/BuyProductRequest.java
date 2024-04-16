package com.personal.dbtransaction.domain;

import lombok.Data;

@Data
public class BuyProductRequest {

    private Long customerId;
    private Long productId;
}
