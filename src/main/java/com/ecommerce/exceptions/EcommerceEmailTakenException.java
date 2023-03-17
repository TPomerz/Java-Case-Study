package com.ecommerce.exceptions;

public class EcommerceEmailTakenException extends RuntimeException{
    public EcommerceEmailTakenException(String msg) {
        super(msg);
    }
}
