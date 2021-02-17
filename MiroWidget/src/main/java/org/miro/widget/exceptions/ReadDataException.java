package org.miro.widget.exceptions;

public class ReadDataException extends RuntimeException {
    public ReadDataException() {
    }

    public ReadDataException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ReadDataException(String message) {
        super(message);
    }
}
