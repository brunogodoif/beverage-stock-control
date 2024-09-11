package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.mappers;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.BeverageType;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.mappers.interfaces.BeverageTypeMapper;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.entities.BeverageTypeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class BeverageTypeMapperImpl implements BeverageTypeMapper {

    private final ModelMapper modelMapper;

    public BeverageTypeMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BeverageType toDomain(BeverageTypeEntity entity) {
        if (entity == null) {
            return null;
        }

        return modelMapper.map(entity, BeverageType.class);
    }

    public BeverageTypeEntity toEntity(BeverageType beverageType) {
        if (beverageType == null) {
            return null;
        }

        return modelMapper.map(beverageType, BeverageTypeEntity.class);
    }
}