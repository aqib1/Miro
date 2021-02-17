package org.miro.widget.exceptions;

public class WidgetCreateException extends RuntimeException {

    public WidgetCreateException() {
    }

    public WidgetCreateException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public WidgetCreateException(String message) {
        super(message);
    }
}
