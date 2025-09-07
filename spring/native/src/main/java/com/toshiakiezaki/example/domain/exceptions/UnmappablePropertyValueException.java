package com.toshiakiezaki.example.domain.exceptions;

public class UnmappablePropertyValueException extends RuntimeException {

    public UnmappablePropertyValueException() {
        super();
    }

    public UnmappablePropertyValueException(String message) {
        super(message);
    }

    public UnmappablePropertyValueException(Throwable cause) {
        super(cause);
    }

    public UnmappablePropertyValueException(String message, Throwable cause) {
        super(message, cause);
    }

}
