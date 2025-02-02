package com.example.boxingarena.exception;

public class ReceiptNotFoundException extends Exception {

    public ReceiptNotFoundException(String message) {
        super(message);
    }

    public ReceiptNotFoundException(Throwable cause) {
        super(cause);
    }

    public ReceiptNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
