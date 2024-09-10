package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados para registrar uma nova bebida no estoque.")
public record RegisterBeverageRequest(

        @Schema(description = "Nome da bebida",
                example = "Coca-Cola")
        @NotNull(message = "Name cannot be null")
        @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
        String name,

        @Schema(description = "ID do tipo de bebida",
                example = "1")
        @NotNull(message = "Beverage Type ID cannot be null")
        @Positive(message = "Beverage Type ID must be greater than zero")
        Integer beverageTypeId,

        @Schema(description = "ID da seção onde a bebida será armazenada",
                example = "2")
        @NotNull(message = "Section ID cannot be null")
        @Positive(message = "Section ID must be greater than zero")
        Integer sectionId,

        @Schema(description = "Volume da bebida em litros",
                example = "100") @Positive(message = "O volume deve ser maior que zero") @NotNull(message = "O volume não pode ser nulo") Integer volume,

        @Schema(description = "Nome do responsável pelo registro",
                example = "João Silva")
        @NotNull(message = "Responsible cannot be null")
        @Size(min = 1, max = 100, message = "Responsible must be between 1 and 100 characters")
        String responsible
) {}
