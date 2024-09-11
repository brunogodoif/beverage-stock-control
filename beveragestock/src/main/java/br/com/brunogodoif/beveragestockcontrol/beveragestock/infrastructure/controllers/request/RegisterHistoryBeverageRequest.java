package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Dados para registrar o histórico da bebida no estoque.")
public record RegisterHistoryBeverageRequest(
        @Schema(description = "Id da Bebida no estoque",
                example = "1")
        @Positive(message = "ID must be greater than zero")
        Integer id,

        @Schema(description = "Volume da bebida na operação",
                example = "100")
        @Positive(message = "O volume deve ser maior que zero")
        @NotNull(message = "O volume não pode ser nulo") Integer volume

) {
}