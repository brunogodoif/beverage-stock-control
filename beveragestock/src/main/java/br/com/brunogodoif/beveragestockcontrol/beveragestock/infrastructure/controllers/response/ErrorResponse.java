package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ErrorResponse(
        @Schema(description = "ID da requisição que causou o erro",
                example = "12345") String requestId,

        @Schema(description = "Caminho da requisição onde o erro ocorreu",
                example = "/api/authenticate") String path,

        @Schema(description = "Data e hora do erro",
                example = "2023-08-20T14:30:00") LocalDateTime timestamp,

        @Schema(description = "Nome da exceção que foi lançada",
                example = "InvalidCredentialsException") String exception,

        @Schema(description = "Mensagem detalhada do erro",
                example = "Credenciais inválidas.") String message
) {
}