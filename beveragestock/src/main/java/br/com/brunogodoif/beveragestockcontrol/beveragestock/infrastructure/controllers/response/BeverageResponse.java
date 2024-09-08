package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.response;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Beverage;

import java.time.LocalDateTime;

public record BeverageResponse(
        Long id,
        String name,
        double volume,
        LocalDateTime entryDate,
        String sectionName,
        boolean alcoholic
) {

    public static BeverageResponse fromDomain(
            Long id,
            String name,
            double volume,
            LocalDateTime entryDate,
            String sectionName,
            boolean alcoholic) {
        return new BeverageResponse(id, name, volume, entryDate, sectionName, alcoholic);
    }

    public static BeverageResponse fromDomain(Beverage beverage) {
        return new BeverageResponse(
                beverage.getId(),
                beverage.getName(),
                beverage.getVolume(),
                beverage.getEntryDate(),
                beverage.getSection() != null ? beverage.getSection().getName() : null,
                beverage.getBeverageType() != null && beverage.getBeverageType().isAlcoholic()
        );
    }
}
