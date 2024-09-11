package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.gateways;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.application.gateways.HistoryGateway;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.History;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.gateways.exceptions.DatabaseOperationException;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.mappers.interfaces.HistoryMapper;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.entities.HistoryEntity;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.repositories.HistoryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Log4j2
public class HistoryGatewayImpl implements HistoryGateway {

    private final HistoryRepository historyRepository;

    @Autowired
    private final HistoryMapper historyMapper;

    public HistoryGatewayImpl(HistoryRepository historyRepository, HistoryMapper historyMapper) {
        this.historyRepository = historyRepository;
        this.historyMapper = historyMapper;
    }

    @Override
    public History save(History history) {
        try {
            HistoryEntity historyEntity = historyMapper.toEntity(history);
            HistoryEntity savedEntity = historyRepository.save(historyEntity);
            return historyMapper.toDomain(savedEntity);
        } catch (DataAccessException e) {
            log.error("Failed to save History: {}", e.getMessage(), e);
            throw new DatabaseOperationException("Failed to save History", e);
        }
    }

    @Override
    public Optional<History> findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number.");
        }

        try {
            return historyRepository.findById(id).map(historyMapper::toDomain);
        } catch (DataAccessException e) {
            log.error("Failed to retrieve History by ID: {}", e.getMessage(), e);
            throw new DatabaseOperationException("Failed to retrieve History by ID", e);
        }
    }

}
