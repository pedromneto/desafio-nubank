package com.nubank.clientmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;
import com.nubank.clientmanager.controller.dto.error.ErrorResponse;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseStatusException handleGeneralException(MethodArgumentTypeMismatchException ex) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST,"Propriedade em formato inválido: " + ex.getPropertyName());
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleClienteException(ClienteNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                                .message(ex.getMessage())
                                .build());
    }
}
