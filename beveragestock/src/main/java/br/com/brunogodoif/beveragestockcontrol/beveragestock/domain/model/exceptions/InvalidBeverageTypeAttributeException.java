package br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.exceptions;

public class InvalidBeverageTypeAttributeException extends RuntimeException {
    public InvalidBeverageTypeAttributeException(String message) {
        super(message);
    }
}