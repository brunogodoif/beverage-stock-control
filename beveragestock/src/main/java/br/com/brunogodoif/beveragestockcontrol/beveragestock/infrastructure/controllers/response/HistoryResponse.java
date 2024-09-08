package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.response;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.History;

import java.time.LocalDateTime;

public record HistoryResponse(
        Long id,
        String operationType,
        double volume,
        LocalDateTime dateRegistry,
        String responsible,
        String sectionName,
        double totalCapacity,
        BeverageResponse beverage
) {

    public static HistoryResponse fromDomain(History history) {
        return new HistoryResponse(history.getId(),
                                   history.getOperationType().name(),
                                   history.getVolume(),
                                   history.getDate(),
                                   history.getResponsible(),
                                   history.getSection().getName(),
                                   history.getSection().getTotalCapacity(),
                                   BeverageResponse.fromDomain(history.getBeverage()));
    }
}