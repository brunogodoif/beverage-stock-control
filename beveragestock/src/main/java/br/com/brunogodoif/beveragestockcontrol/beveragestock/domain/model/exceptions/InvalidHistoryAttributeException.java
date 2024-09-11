package br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.exceptions;

public class InvalidHistoryAttributeException extends RuntimeException {
    public InvalidHistoryAttributeException(String message) {
        super(message);
    }
}