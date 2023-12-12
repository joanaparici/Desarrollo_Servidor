package com.Proyecto.poyect1.http_errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.Proyecto.poyect1.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler {

@ResponseStatus(HttpStatus.NOT_FOUND)  
@ExceptionHandler({//CAPTURAME LAS EXCEPCIONES DE ESTE TIPO...
    ResourceNotFoundException.class
})
@ResponseBody//LA RESPUESTA QUE VAMOS A DAR, EN ESTE CASO DEVUELVE UNA FUNCION QUE ENVIA UN MENSAJE
public ErrorMessage notFoundRequest(Exception exception) {
    return new ErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
}

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@ExceptionHandler({
        Exception.class
})
@ResponseBody
public ErrorMessage exception(Exception exception) {
    exception.printStackTrace();
    return new ErrorMessage("Internal error", HttpStatus.INTERNAL_SERVER_ERROR.value());
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
@ExceptionHandler(HttpMessageNotReadableException.class)
@ResponseBody
public ErrorMessage badRequest(HttpMessageNotReadableException exception) {
    return new ErrorMessage("Invalid request body", HttpStatus.BAD_REQUEST.value());
}
}
