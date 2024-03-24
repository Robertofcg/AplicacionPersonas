package com.example.demo.Exception;

public class PersonaNotFoundException extends RuntimeException {
    public PersonaNotFoundException(String mensaje) {
        super(mensaje);
        
    }
}
