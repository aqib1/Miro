package org.miro.widget.exceptions;

public class WidgetNotFoundException extends RuntimeException {

    public WidgetNotFoundException() {
    }

    public WidgetNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public WidgetNotFoundException(String message) {
        super(message);
    }
}
