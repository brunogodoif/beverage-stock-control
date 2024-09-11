package br.com.brunogodoif.beveragestockcontrol.beveragestock.application.orchestrators.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidOperationTypeException extends RuntimeException {
    public InvalidOperationTypeException(String message) {
        super(message);
    }
}