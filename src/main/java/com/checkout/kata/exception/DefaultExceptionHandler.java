package com.checkout.kata.exception;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ConditionalOnProperty(name = "app.swagger.doc-generation", havingValue = "false", matchIfMissing = true)
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UseCaseException.class)
    public ResponseEntity<Object> handleResourceException(UseCaseException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
