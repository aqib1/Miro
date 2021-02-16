package org.miro.widget.exceptions;

public class RequestException extends RuntimeException {
    public RequestException() {
    }

    public RequestException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public RequestException(String message) {
        super(message);
    }
}
