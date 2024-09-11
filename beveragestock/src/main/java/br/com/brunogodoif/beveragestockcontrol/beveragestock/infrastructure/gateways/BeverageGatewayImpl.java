package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.gateways;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.application.gateways.BeverageGatewayInterface;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Beverage;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.gateways.exceptions.DatabaseOperationException;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.mappers.interfaces.BeverageMapper;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.repositories.BeverageRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Component
public class BeverageGatewayImpl implements BeverageGatewayInterface {

    private final BeverageRepository beverageRepository;

    @Autowired
    private BeverageMapper beverageMapper;

    public BeverageGatewayImpl(BeverageRepository beverageRepository, BeverageMapper beverageMapper) {
        this.beverageRepository = beverageRepository;
        this.beverageMapper = beverageMapper;
    }

    @Override
    public Beverage save(Beverage beverage) {
        try {
            return beverageMapper.toDomain(beverageRepository.save(beverageMapper.toEntity(beverage)));
        } catch (DataAccessException e) {
            log.error("Failed to save Beverage: {}", e.getMessage(), e);
            throw new DatabaseOperationException("Failed to save Beverage", e);
        }
    }

    @Override
    public Optional<Beverage> findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number.");
        }

        try {
            return beverageRepository.findById(id).map(beverageMapper::toDomain);
        } catch (DataAccessException e) {
            log.error("Failed to retrieve Beverage by ID: {}", e.getMessage(), e);
            throw new DatabaseOperationException("Failed to retrieve Beverage by ID", e);
        }
    }

    @Override
    public List<Beverage> findAllBySectionId(Long sectionId) {
        if (sectionId == null || sectionId <= 0) {
            throw new IllegalArgumentException("Section ID must be a positive number.");
        }

        try {
            return beverageRepository.findAllBySectionId(sectionId).stream().map(beverageMapper::toDomain)
                                     .collect(Collectors.toList());
        } catch (DataAccessException e) {
            log.error("Failed to retrieve Beverages by Section ID: {}", e.getMessage(), e);
            throw new DatabaseOperationException("Failed to retrieve Beverages by Section ID", e);
        }
    }
}