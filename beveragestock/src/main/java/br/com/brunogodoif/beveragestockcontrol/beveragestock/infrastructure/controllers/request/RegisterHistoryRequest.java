package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados para registro de histórico de entrada ou saída de bebidas.")
public record RegisterHistoryRequest(
        @Schema(description = "Tipo de operação (ENTRY ou EXIT)", example = "ENTRY")
        @NotNull(message = "Operation type cannot be null")
        String operationType,

        @Schema(description = "Nome do responsável pela operação", example = "João Silva")
        @NotNull(message = "Responsible cannot be null")
        @Size(min = 1, max = 100, message = "Responsible must be between 1 and 100 characters")
        String responsible,

        @Schema(description = "Dados da bebida")
        @NotNull(message = "Beverage cannot be null")
        RegisterHistoryBeverageRequest beverage
) {
}