package com.personal.dbtransaction.domain;

import lombok.Data;

@Data
public class BuyProductRequest {

    private long customerId;
    private long productId;
    private int quantity;
    private long delay;
}
