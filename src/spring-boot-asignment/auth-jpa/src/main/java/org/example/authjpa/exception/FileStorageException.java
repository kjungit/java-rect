package org.example.authjpa.exception;


public class FileStorageException extends RuntimeException {
    public FileStorageException( String message, Throwable cause ) {
        super(message, cause);
    }
}
