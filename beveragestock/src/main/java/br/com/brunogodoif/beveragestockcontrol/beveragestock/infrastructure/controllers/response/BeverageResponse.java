package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.response;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Beverage;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Resposta contendo os detalhes de uma bebida registrada.")
public record BeverageResponse(

        @Schema(description = "ID da bebida",
                example = "1") Long id,

        @Schema(description = "Nome da bebida",
                example = "Coca-Cola") String name,

        @Schema(description = "Volume da bebida",
                example = "100") double volume,

        @Schema(description = "Data de entrada da bebida",
                example = "2024-09-08T10:14:34") LocalDateTime entryDate,

        @Schema(description = "Nome da seção onde a bebida está armazenada",
                example = "Seção 1") String sectionName,

        @Schema(description = "Indica se a bebida é alcoólica",
                example = "false") boolean alcoholic
) {

    public static BeverageResponse fromDomain(Long id, String name, double volume, LocalDateTime entryDate,
                                              String sectionName, boolean alcoholic
                                             ) {
        return new BeverageResponse(id, name, volume, entryDate, sectionName, alcoholic);
    }

    public static BeverageResponse fromDomain(Beverage beverage) {
        return new BeverageResponse(beverage.getId(), beverage.getName(), beverage.getVolume(), beverage.getEntryDate(),
                                    beverage.getSection() != null ? beverage.getSection().getName() : null,
                                    beverage.getBeverageType() != null && beverage.getBeverageType().isAlcoholic());
    }
}
