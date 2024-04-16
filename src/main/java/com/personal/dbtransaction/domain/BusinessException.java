package com.personal.dbtransaction.domain;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
