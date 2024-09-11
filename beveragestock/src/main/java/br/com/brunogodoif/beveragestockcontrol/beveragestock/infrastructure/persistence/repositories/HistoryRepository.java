package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.repositories;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.entities.HistoryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {

    List<HistoryEntity> findAllBySectionId(Long sectionId);

    List<HistoryEntity> findBySectionId(Long sectionId, Pageable pageable);

    List<HistoryEntity> findByBeverageBeverageTypeId(Long beverageTypeId, Pageable pageable);

    List<HistoryEntity> findBySectionIdAndBeverageBeverageTypeId(Long sectionId, Long beverageTypeId,
                                                                 Pageable pageable);
}