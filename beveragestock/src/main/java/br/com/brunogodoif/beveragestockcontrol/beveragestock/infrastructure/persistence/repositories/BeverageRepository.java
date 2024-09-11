package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.repositories;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.entities.BeverageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeverageRepository extends JpaRepository<BeverageEntity, Long> {
    List<BeverageEntity> findAllBySectionId(Long sectionId);
}