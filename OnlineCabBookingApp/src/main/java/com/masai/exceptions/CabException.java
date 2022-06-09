package com.masai.exceptions;

public class CabException extends RuntimeException {
    public CabException() {
    }

    public CabException(String message) {
        super(message);
    }
}
