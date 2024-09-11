package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.mappers.interfaces;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Section;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.entities.SectionEntity;

public interface SectionMapper {

    Section toDomain(SectionEntity entity);

    SectionEntity toEntity(Section domain);
}