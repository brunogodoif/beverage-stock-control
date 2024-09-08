package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.exceptions.*;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.response.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    private static final Map<Class<? extends Exception>, HttpStatus> exceptionStatusMap = new HashMap<>();

    static {
        exceptionStatusMap.put(MethodArgumentNotValidException.class, HttpStatus.BAD_REQUEST);
        exceptionStatusMap.put(InvalidBeverageAttributeException.class, HttpStatus.BAD_REQUEST);
        exceptionStatusMap.put(InsufficientCapacityException.class, HttpStatus.BAD_REQUEST);
        exceptionStatusMap.put(SectionTypeConflictException.class, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
        return buildErrorResponse(ex,
                                  request,
                                  ex.getMessage().isEmpty() ? "Ocorreu um erro interno na aplicação." : ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex,
                                                                    WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String validationMessage = fieldErrors.isEmpty() ? "Erro de validação." : fieldErrors.get(0).getDefaultMessage();
        return buildErrorResponse(ex, request, validationMessage);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(Exception ex, WebRequest request, String message) {
        String requestId = request.getHeader("X-Request-Id");
        String path = request.getDescription(false).replace("uri=", "");
        LocalDateTime timestamp = LocalDateTime.now();
        String exception = ex.getClass().getSimpleName();

        ErrorResponse errorResponse = new ErrorResponse(requestId != null ? requestId : "N/A",
                                                        path,
                                                        timestamp,
                                                        exception,
                                                        message);

        HttpStatus status = determineHttpStatus(ex);
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), status);
    }

    private HttpStatus determineHttpStatus(Exception ex) {
        HttpStatus status = exceptionStatusMap.get(ex.getClass());
        if (status != null) {
            return status;
        }

        if (ex.getClass().isAnnotationPresent(ResponseStatus.class)) {
            return ex.getClass().getAnnotation(ResponseStatus.class).code();
        }

        if (ex instanceof ResponseStatusException) {
            return (HttpStatus) ((ResponseStatusException) ex).getStatusCode();
        }

        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
