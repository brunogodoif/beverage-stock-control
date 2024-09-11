package br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.orchestrators;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.History;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.request.RegisterHistoryRequest;

public interface RegisterHistoryOrchestratorInterface {
    History registerHistory(RegisterHistoryRequest request);
}
