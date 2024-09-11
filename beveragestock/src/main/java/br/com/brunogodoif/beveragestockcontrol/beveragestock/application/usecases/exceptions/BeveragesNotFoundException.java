package br.com.brunogodoif.beveragestockcontrol.beveragestock.application.usecases.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BeveragesNotFoundException extends RuntimeException {
    public BeveragesNotFoundException(String s) {
    }
}
