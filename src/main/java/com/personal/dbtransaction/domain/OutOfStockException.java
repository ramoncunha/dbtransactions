package com.personal.dbtransaction.domain;

import com.personal.dbtransaction.domain.BusinessException;

public class OutOfStockException extends BusinessException {

    public OutOfStockException(String message) {
        super(message);
    }
}
