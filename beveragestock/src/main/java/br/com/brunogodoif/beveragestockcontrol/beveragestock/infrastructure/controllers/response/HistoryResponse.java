package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.response;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.History;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Resposta com os dados de um histórico de entrada ou saída de bebida.")
public record HistoryResponse(
        @Schema(description = "ID do histórico",
                example = "1") Long id,

        @Schema(description = "Tipo de operação (ENTRY ou EXIT)",
                example = "ENTRY") String operationType,

        @Schema(description = "Volume da bebida na operação",
                example = "100") double volume,

        @Schema(description = "Data e hora da operação",
                example = "2024-09-08T10:14:34") LocalDateTime dateRegistry,

        @Schema(description = "Responsável pela operação",
                example = "João Silva") String responsible,

        @Schema(description = "Nome da seção onde a operação foi realizada",
                example = "Seção 1") String sectionName,

        @Schema(description = "Capacidade total da seção",
                example = "500.0") double totalCapacity,

        @Schema(description = "Dados da bebida") BeverageResponse beverage
) {

    public static HistoryResponse fromDomain(History history) {
        return new HistoryResponse(history.getId(), history.getOperationType().name(), history.getVolume(),
                                   history.getDate(), history.getResponsible(), history.getSection().getName(),
                                   history.getSection().getTotalCapacity(),
                                   BeverageResponse.fromDomain(history.getBeverage()));
    }
}