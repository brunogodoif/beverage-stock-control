package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.repositories;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.entities.SectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<SectionEntity, Long> {

}