package org.example.userunittestmockitointegration.exception;

public class InvalidEmailException extends  RuntimeException{
    public InvalidEmailException(String message) {
        super(message);
    }
}
