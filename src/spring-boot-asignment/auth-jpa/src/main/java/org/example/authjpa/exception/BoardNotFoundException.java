package org.example.authjpa.exception;

public class BoardNotFoundException extends RuntimeException {
    public BoardNotFoundException(String message) { super(message); }
}
