package com.personal.dbtransaction;

import lombok.Data;

@Data
public class BuyProductRequest {

    private Long customerId;
    private Long productId;
    private int quantity;
}
