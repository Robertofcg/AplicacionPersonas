package com.example.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error");
    }

    @ExceptionHandler(PersonaNotFoundException.class)
    public ResponseEntity<?> handlePersonaNotFoundException(PersonaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada");
    }
    
}
