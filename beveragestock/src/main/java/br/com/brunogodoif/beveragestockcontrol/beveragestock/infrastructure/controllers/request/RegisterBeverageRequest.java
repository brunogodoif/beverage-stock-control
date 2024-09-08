package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record RegisterBeverageRequest(

        @NotNull(message = "Name cannot be null")
        @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
        String name,

        @NotNull(message = "Beverage Type ID cannot be null")
        @Positive(message = "Beverage Type ID must be greater than zero")
        Integer beverageTypeId,

        @NotNull(message = "Section ID cannot be null")
        @Positive(message = "Section ID must be greater than zero")
        Integer sectionId,

        @Positive(message = "Volume must be greater than zero")
        double volume,

        @NotNull(message = "Responsible cannot be null")
        @Size(min = 1, max = 100, message = "Responsible must be between 1 and 100 characters")
        String responsible
) {}
