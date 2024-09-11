package br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.exceptions;

public class InsufficientCapacityException extends RuntimeException {
    public InsufficientCapacityException(String message) {
        super(message);
    }
}