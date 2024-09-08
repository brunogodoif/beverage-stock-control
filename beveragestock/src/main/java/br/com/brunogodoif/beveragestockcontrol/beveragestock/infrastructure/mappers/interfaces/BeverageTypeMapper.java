package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.mappers.interfaces;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.BeverageType;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.entities.BeverageTypeEntity;
public interface BeverageTypeMapper {

    BeverageType toDomain(BeverageTypeEntity entity);

    BeverageTypeEntity toEntity(BeverageType domain);
}