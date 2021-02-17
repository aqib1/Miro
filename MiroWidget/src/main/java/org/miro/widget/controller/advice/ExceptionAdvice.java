package org.miro.widget.controller.advice;

import org.miro.widget.dto.response.ResponseError;
import org.miro.widget.exceptions.ReadDataException;
import org.miro.widget.exceptions.RequestException;
import org.miro.widget.exceptions.WidgetCreateException;
import org.miro.widget.exceptions.WidgetNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.EmptyStackException;
import java.util.Optional;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(value
            = EmptyStackException.class)
    public ResponseEntity<ResponseError> handleEmptyStackException(
            EmptyStackException e) {
        String error = Optional.ofNullable(e.getMessage()).orElse(e.getClass().getName())
                + " [Internal server exception! => (EmptyStackException)]";
        ResponseError errorResponse = ResponseError.builder()
                .createdAt(LocalDateTime.now().toString())
                .detailedMessage(error)
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .exceptionName(EmptyStackException.class.getName())
                .errorMessage(e.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value
            = ReadDataException.class)
    public ResponseEntity<ResponseError> handleReadDataException(
            ReadDataException e) {
        String error = Optional.ofNullable(e.getMessage()).orElse(e.getClass().getName())
                + " [Internal server exception! => (ReadDataException)]";
        ResponseError errorResponse = ResponseError.builder()
                .createdAt(LocalDateTime.now().toString())
                .detailedMessage(error)
                .errorCode(HttpStatus.NOT_IMPLEMENTED.value())
                .exceptionName(ReadDataException.class.getName())
                .errorMessage(e.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler(value
            = RequestException.class)
    public ResponseEntity<ResponseError> handleRequestException(
            RequestException e) {
        String error = Optional.ofNullable(e.getMessage()).orElse(e.getClass().getName())
                + " [Internal server exception! => (RequestException)]";
        ResponseError errorResponse = ResponseError.builder()
                .createdAt(LocalDateTime.now().toString())
                .detailedMessage(error)
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .exceptionName(RequestException.class.getName())
                .errorMessage(e.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value
            = WidgetNotFoundException.class)
    public ResponseEntity<ResponseError> handleWidgetNotFoundException(
            WidgetNotFoundException e) {
        String error = Optional.ofNullable(e.getMessage()).orElse(e.getClass().getName())
                + " [Internal server exception! => (WidgetNotFoundException)]";
        ResponseError errorResponse = ResponseError.builder()
                .createdAt(LocalDateTime.now().toString())
                .detailedMessage(error)
                .errorCode(HttpStatus.NOT_FOUND.value())
                .exceptionName(WidgetNotFoundException.class.getName())
                .errorMessage(e.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value
            = WidgetCreateException.class)
    public ResponseEntity<ResponseError> handleWidgetCreateException(
            WidgetCreateException e) {
        String error = Optional.ofNullable(e.getMessage()).orElse(e.getClass().getName())
                + " [Internal server exception! => (WidgetCreateException)]";
        ResponseError errorResponse = ResponseError.builder()
                .createdAt(LocalDateTime.now().toString())
                .detailedMessage(error)
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .exceptionName(WidgetCreateException.class.getName())
                .errorMessage(e.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
