package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.mappers.interfaces;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Beverage;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.entities.BeverageEntity;

public interface BeverageMapper {

    Beverage toDomain(BeverageEntity entity);

    BeverageEntity toEntity(Beverage domain);
}