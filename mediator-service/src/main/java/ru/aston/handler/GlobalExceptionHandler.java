package ru.aston.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> notFound(Exception exception) {
        HttpClientErrorException e = (HttpClientErrorException) exception;

        return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
    }
}
