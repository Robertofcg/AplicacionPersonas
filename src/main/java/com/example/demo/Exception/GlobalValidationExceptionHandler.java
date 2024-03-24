package com.example.demo.Exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalValidationExceptionHandler extends ResponseEntityExceptionHandler {

	    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error de integridad de datos: " + ex.getMessage());
    }
    
    @ExceptionHandler(PersonaNotFoundException.class)
    public ResponseEntity<String> handlePersonaNotFoundException(PersonaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        java.util.Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("mensaje", "Se encontraron errores de validación");
        body.put("detalles", constraintViolations.stream()
                .map(ConstraintViolation::getMessage)
                .toArray());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }   

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String errorMessage = "Error al procesar la solicitud: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}
    
    
    
}
