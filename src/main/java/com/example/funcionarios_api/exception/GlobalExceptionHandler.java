package com.example.funcionarios_api.exception;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratarValidacao(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        List<Map<String, String>> fields = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> Map.of(
                        "name", error.getField(),
                        "message", error.getDefaultMessage()
                ))
                .toList();

        Map<String, Object> body = Map.of(
                "error", "Invalid input",
                "fields", fields,
                "path", request.getRequestURI()
        );

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<?> tratarCampoInvalido(
            InvalidFieldException ex,
            HttpServletRequest request) {

        Map<String, Object> body = Map.of(
                "error", "Invalid input",
                "fields", List.of(Map.of(
                        "name", ex.getField(),
                        "message", ex.getMessage()
                )),
                "path", request.getRequestURI()
        );

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> tratarNaoEncontrado(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", ex.getMessage()));
    }
}