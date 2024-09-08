package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.gateways.exceptions;

public class DatabaseOperationException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Falha ao acessar base de dados, tente novamente mais tarde.";

    public DatabaseOperationException() {
        super(DEFAULT_MESSAGE);
    }

    public DatabaseOperationException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public DatabaseOperationException(String customMessage, Throwable cause) {
        super(customMessage, cause);
    }

}