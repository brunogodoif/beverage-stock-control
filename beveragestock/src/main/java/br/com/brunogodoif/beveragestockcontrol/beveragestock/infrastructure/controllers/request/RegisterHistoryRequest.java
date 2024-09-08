package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record RegisterHistoryRequest(
        @NotNull(message = "Operation type cannot be null")
        String operationType,

        @NotNull(message = "Responsible cannot be null")
        @Size(min = 1, max = 100, message = "Responsible must be between 1 and 100 characters")
        String responsible,

        @NotNull(message = "Beverage cannot be null")
        RegisterHistoryBeverageRequest beverage
) {
}