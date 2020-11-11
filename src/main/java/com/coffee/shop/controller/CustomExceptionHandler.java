package com.coffee.shop.controller;

import com.coffee.shop.dto.FailApiResponse;
import com.coffee.shop.util.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<FailApiResponse> handleRuntimeException(RuntimeException e) {
        logger.error("Exception occurred while trying to execute", e);
        return new ResponseEntity<>(new FailApiResponse(StatusCode.E300, StatusCode.E300.getDescription()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


