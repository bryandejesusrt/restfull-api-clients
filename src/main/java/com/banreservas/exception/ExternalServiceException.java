package com.banreservas.exception;

//impor jakarta exception
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;


public class ExternalServiceException extends RuntimeException {
    public ExternalServiceException(String message) {
        super(message);
    }

    public ExternalServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
    