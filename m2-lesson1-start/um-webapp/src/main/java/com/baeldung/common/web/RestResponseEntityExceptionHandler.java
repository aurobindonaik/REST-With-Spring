package com.baeldung.common.web;

import com.baeldung.common.web.exception.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger log = LoggerFactory.getLogger(getClass());

    public RestResponseEntityExceptionHandler() {
        super();
    }

    // API

    // 400


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, message(HttpStatus.BAD_REQUEST, ex), headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, message(HttpStatus.BAD_REQUEST, ex), headers, HttpStatus.BAD_REQUEST, request);
    }

    private ApiError message(final HttpStatus httpStatus, final Exception ex) {
        String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
        String devMessage = ex.getClass().getSimpleName();

        return new ApiError(httpStatus.value(), message, devMessage);
    }
}
