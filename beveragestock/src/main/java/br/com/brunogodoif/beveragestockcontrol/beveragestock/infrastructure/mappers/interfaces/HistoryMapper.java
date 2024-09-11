package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.mappers.interfaces;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.History;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.entities.HistoryEntity;

public interface HistoryMapper {

    History toDomain(HistoryEntity entity);

    HistoryEntity toEntity(History domain);
}