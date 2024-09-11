package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.repositories;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.entities.BeverageTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeverageTypeRepository extends JpaRepository<BeverageTypeEntity, Long> {

    Optional<BeverageTypeEntity> findByName(String name);

}