package com.Proyecto.poyect1.http_errors;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ErrorMessage {

    private String message;
    private int code;

    public ErrorMessage(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
