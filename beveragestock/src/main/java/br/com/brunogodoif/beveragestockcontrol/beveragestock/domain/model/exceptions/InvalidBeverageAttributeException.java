package br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.exceptions;

public class InvalidBeverageAttributeException extends RuntimeException {
    public InvalidBeverageAttributeException(String message) {
        super(message);
    }
}
