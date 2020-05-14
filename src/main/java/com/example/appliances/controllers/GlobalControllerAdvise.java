package com.example.appliances.controllers;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvise {

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<BaseError> handleRequestFormatError(ChangeSetPersister.NotFoundException e) {
        final BaseError error = new BaseError(HttpStatus.NOT_FOUND, "Record not found");
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BaseError> handleRequestFormatError(HttpMessageNotReadableException e) {
        final BaseError error = new BaseError(HttpStatus.BAD_REQUEST, "Failed to parse input");
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseError> handleAll(Exception e) {
        final BaseError error = new BaseError(HttpStatus.INTERNAL_SERVER_ERROR, e);
        return new ResponseEntity<>(error, error.getStatus());
    }
}

class BaseError {
    private final String error;
    private final HttpStatus status;

    public BaseError(final HttpStatus status, final Exception e) {
        this.error = e.getMessage();
        this.status = status;
    }

    public BaseError(final HttpStatus status, final String message) {
        this.error = message;
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public HttpStatus getStatus() {
        return status;
    }
}