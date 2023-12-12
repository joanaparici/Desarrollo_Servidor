package com.Proyecto.poyect1.exceptions;

public class DBConnectionException extends RuntimeException {
    private static final String DESCRIPTION = "Can't establish connection";


    public DBConnectionException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}
