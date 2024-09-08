package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record RegisterHistoryBeverageRequest(
        @Positive(message = "ID must be greater than zero")
        Integer id,

        @Positive(message = "Volume must be greater than zero")
        double volume
) {
}