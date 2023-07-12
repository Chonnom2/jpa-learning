package com.jpabook.jpashop.exception;

public class NotEnoughStockExeption extends  RuntimeException{
    public NotEnoughStockExeption() {
        super();
    }

    public NotEnoughStockExeption(String message) {
        super(message);
    }

    public NotEnoughStockExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughStockExeption(Throwable cause) {
        super(cause);
    }

    protected NotEnoughStockExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
