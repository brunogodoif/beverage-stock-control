package br.com.brunogodoif.beveragestockcontrol.beveragestock.application.gateways;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Section;

import java.util.List;
import java.util.Optional;

public interface SectionGateway {

    Section save(Section section);

    Optional<Section> findById(Long id);

}