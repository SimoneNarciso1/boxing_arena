package com.example.boxingarena.exception;

public class InvalidFormatException extends Exception{

    public InvalidFormatException(String message){
        super(message);
    }

    public InvalidFormatException(Throwable cause){
        super(cause);
    }

    public InvalidFormatException(String message, Throwable cause) {
        super(message,cause);
    }


}
