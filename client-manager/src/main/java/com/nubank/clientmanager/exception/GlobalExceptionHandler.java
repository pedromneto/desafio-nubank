package com.nubank.clientmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseStatusException handleGeneralException(MethodArgumentTypeMismatchException ex) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST,"Propriedade em formato inválido: " + ex.getPropertyName());
    }
}
