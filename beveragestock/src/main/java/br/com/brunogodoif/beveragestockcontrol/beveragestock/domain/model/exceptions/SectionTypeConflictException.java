package br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.exceptions;

public class SectionTypeConflictException extends RuntimeException {
    public SectionTypeConflictException(String message) {
        super(message);
    }
}