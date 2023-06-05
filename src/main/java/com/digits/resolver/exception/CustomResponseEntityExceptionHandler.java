package com.digits.resolver.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({SolutionNotFoundException.class, TrivialSolutionException.class, InvalidInputException.class})
    public ResponseEntity<Object> handleInvalidInputException(Exception ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }
}
