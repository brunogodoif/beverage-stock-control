package br.com.brunogodoif.beveragestockcontrol.beveragestock.application.usecases.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BeverageNotFoundException extends RuntimeException {
    public BeverageNotFoundException(String message) {
        super(message);
    }
}