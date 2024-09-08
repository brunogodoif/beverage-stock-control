package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.mappers;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Beverage;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.BeverageType;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Section;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.mappers.interfaces.BeverageMapper;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.entities.BeverageEntity;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.entities.BeverageTypeEntity;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.entities.SectionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BeverageMapperImpl implements BeverageMapper {

    private final ModelMapper modelMapper;

    public BeverageMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Beverage toDomain(BeverageEntity entity) {
        if (entity == null) {
            return null;
        }

        BeverageType beverageType = modelMapper.map(entity.getBeverageType(), BeverageType.class);
        Section section = modelMapper.map(entity.getSection(), Section.class);

        return new Beverage(entity.getId(),
                            entity.getName(),
                            beverageType,
                            entity.getVolume(),
                            entity.getEntryDate(),
                            section);
    }

    @Override
    public BeverageEntity toEntity(Beverage beverage) {
        if (beverage == null) {
            return null;
        }

        BeverageTypeEntity beverageTypeEntity = modelMapper.map(beverage.getBeverageType(), BeverageTypeEntity.class);
        SectionEntity sectionEntity = modelMapper.map(beverage.getSection(), SectionEntity.class);

        BeverageEntity entity = modelMapper.map(beverage, BeverageEntity.class);
        entity.setBeverageType(beverageTypeEntity);
        entity.setSection(sectionEntity);

        return entity;
    }
}