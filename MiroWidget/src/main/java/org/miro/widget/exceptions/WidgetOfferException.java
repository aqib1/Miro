package org.miro.widget.exceptions;

public class WidgetOfferException extends RuntimeException {

    public WidgetOfferException() {
    }

    public WidgetOfferException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public WidgetOfferException(String message) {
        super(message);
    }
}
