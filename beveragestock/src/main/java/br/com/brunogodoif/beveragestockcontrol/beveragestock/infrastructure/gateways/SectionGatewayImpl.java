package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.gateways;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.application.gateways.SectionGateway;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Section;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.gateways.exceptions.DatabaseOperationException;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.mappers.interfaces.SectionMapper;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.entities.SectionEntity;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.repositories.SectionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Log4j2
public class SectionGatewayImpl implements SectionGateway {

    private final SectionRepository sectionRepository;

    @Autowired
    private final SectionMapper sectionMapper;

    public SectionGatewayImpl(SectionRepository sectionRepository, @Lazy SectionMapper sectionMapper) {
        this.sectionRepository = sectionRepository;
        this.sectionMapper = sectionMapper;
    }

    @Override
    public Section save(Section section) {
        try {
            SectionEntity entity = sectionMapper.toEntity(section);
            SectionEntity savedEntity = sectionRepository.save(entity);
            return sectionMapper.toDomain(savedEntity);
        } catch (DataAccessException e) {
            log.error("Failed to save Section: {}", e.getMessage(), e);
            throw new DatabaseOperationException("Failed to save Section", e);
        }
    }

    @Override
    public Optional<Section> findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number.");
        }

        try {
            return sectionRepository.findById(id).map(sectionMapper::toDomain);
        } catch (DataAccessException e) {
            log.error("Failed to retrieve Section by ID: {}", e.getMessage(), e);
            throw new DatabaseOperationException("Failed to retrieve Section by ID", e);
        }
    }

}