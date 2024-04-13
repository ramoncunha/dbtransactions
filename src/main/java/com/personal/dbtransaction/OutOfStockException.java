package com.personal.dbtransaction;

public class OutOfStockException extends BusinessException {

    public OutOfStockException(String message) {
        super(message);
    }
}
