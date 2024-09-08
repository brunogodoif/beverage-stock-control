package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.gateways;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.application.gateways.BeverageTypeGateway;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.BeverageType;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.gateways.exceptions.DatabaseOperationException;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.mappers.interfaces.BeverageTypeMapper;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.repositories.BeverageTypeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Log4j2
public class BeverageTypeGatewayImpl implements BeverageTypeGateway {

    private final BeverageTypeRepository beverageTypeRepository;

    @Autowired
    private final BeverageTypeMapper beverageTypeMapper;

    public BeverageTypeGatewayImpl(BeverageTypeRepository beverageTypeRepository,
                                   BeverageTypeMapper beverageTypeMapper) {
        this.beverageTypeRepository = beverageTypeRepository;
        this.beverageTypeMapper = beverageTypeMapper;
    }

    @Override
    public BeverageType save(BeverageType beverageType) {
        try {
            return beverageTypeMapper.toDomain(beverageTypeRepository.save(beverageTypeMapper.toEntity(beverageType)));
        } catch (DataAccessException e) {
            log.error("Failed to save BeverageType: {}", e.getMessage(), e);
            throw new DatabaseOperationException("Failed to save BeverageType", e);
        }
    }

    @Override
    public Optional<BeverageType> findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number.");
        }

        try {
            return beverageTypeRepository.findById(id).map(beverageTypeMapper::toDomain);
        } catch (DataAccessException e) {
            log.error("Failed to retrieve BeverageType by ID: {}", e.getMessage(), e);
            throw new DatabaseOperationException("Failed to retrieve BeverageType by ID", e);
        }
    }

}