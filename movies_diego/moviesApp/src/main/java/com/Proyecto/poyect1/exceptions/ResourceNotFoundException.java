package com.Proyecto.poyect1.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    
    private static final String DESCRIPTION = "Resource not found";

    public ResourceNotFoundException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}
