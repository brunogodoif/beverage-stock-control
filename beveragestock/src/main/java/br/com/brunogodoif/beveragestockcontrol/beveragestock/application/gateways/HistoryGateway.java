package br.com.brunogodoif.beveragestockcontrol.beveragestock.application.gateways;


import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.History;

import java.util.List;
import java.util.Optional;

public interface HistoryGateway {

    History save(History history);

    Optional<History> findById(Long id);

}