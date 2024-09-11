package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.mappers;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Beverage;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.History;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Section;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.mappers.interfaces.SectionMapper;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.entities.BeverageEntity;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.entities.SectionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SectionMapperImpl implements SectionMapper {

    private final ModelMapper modelMapper;

    public SectionMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Section toDomain(SectionEntity sectionEntity) {
        if (sectionEntity == null) {
            return null;
        }

        Set<Beverage> beverages = sectionEntity.getBeverages().stream()
                                               .map(beverageEntity -> modelMapper.map(beverageEntity, Beverage.class))
                                               .collect(Collectors.toSet());

        Set<History> histories = sectionEntity.getHistories().stream()
                                              .map(historyEntity -> modelMapper.map(historyEntity, History.class))
                                              .collect(Collectors.toSet());

        return new Section(
                sectionEntity.getId(),
                sectionEntity.getName(),
                sectionEntity.getTotalCapacity(),
                sectionEntity.getUsedCapacity(),
                beverages,
                histories
        );
    }

    public SectionEntity toEntity(Section section) {
        if (section == null) {
            return null;
        }

        SectionEntity sectionEntity = modelMapper.map(section, SectionEntity.class);

        Set<BeverageEntity> beverageEntities = section.getBeverages().stream()
                                                      .map(beverage -> modelMapper.map(beverage, BeverageEntity.class))
                                                      .collect(Collectors.toSet());

        sectionEntity.setBeverages(beverageEntities);

        return sectionEntity;
    }
}
