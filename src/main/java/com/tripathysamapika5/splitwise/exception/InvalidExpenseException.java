package com.tripathysamapika5.splitwise.exception;

public class InvalidExpenseException extends RuntimeException{

    public InvalidExpenseException(String message, Throwable cause) {
        super(message, cause);
    }
    public InvalidExpenseException(String message) {
        super(message);
    }
}
