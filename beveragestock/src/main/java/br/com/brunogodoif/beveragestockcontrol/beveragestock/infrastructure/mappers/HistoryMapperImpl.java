package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.mappers;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Beverage;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.History;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.OperationType;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Section;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.mappers.interfaces.HistoryMapper;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.entities.BeverageEntity;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.entities.HistoryEntity;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.entities.OperationTypeEntity;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.entities.SectionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class HistoryMapperImpl implements HistoryMapper {

    private final ModelMapper modelMapper;

    public HistoryMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public History toDomain(HistoryEntity entity) {
        if (entity == null) {
            return null;
        }

        Beverage beverage = null;
        if (entity.getBeverage() != null) {
            beverage = modelMapper.map(entity.getBeverage(), Beverage.class);
        }

        Section section = null;
        if (entity.getSection() != null) {
            section = modelMapper.map(entity.getSection(), Section.class);
        }

        return new History(
                entity.getId(),
                OperationType.valueOf(entity.getOperationType().toString()),
                entity.getVolume(),
                entity.getDate(),
                entity.getResponsible(),
                section,
                beverage
        );
    }

    @Override
    public HistoryEntity toEntity(History history) {
        if (history == null) {
            return null;
        }

        BeverageEntity beverageEntity = null;
        if (history.getBeverage() != null) {
            beverageEntity = modelMapper.map(history.getBeverage(), BeverageEntity.class);
        }

        SectionEntity sectionEntity = null;
        if (history.getSection() != null) {
            sectionEntity = modelMapper.map(history.getSection(), SectionEntity.class);
        }

        HistoryEntity entity = new HistoryEntity();
        entity.setId(history.getId());
        entity.setOperationType(OperationTypeEntity.valueOf(history.getOperationType().toString()));
        entity.setVolume(history.getVolume());
        entity.setDate(history.getDate());
        entity.setResponsible(history.getResponsible());
        entity.setSection(sectionEntity);
        entity.setBeverage(beverageEntity);

        return entity;
    }
}