package org.miro.widget.controller.advice;

import org.miro.widget.dto.response.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

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
                + " [Internal server exception! => (RuntimeException)]";
        ResponseError errorResponse = ResponseError.builder()
                .createdAt(LocalDateTime.now().toString())
                .detailedMessage(error)
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .exceptionName(EmptyStackException.class.getName())
                .errorMessage(e.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
