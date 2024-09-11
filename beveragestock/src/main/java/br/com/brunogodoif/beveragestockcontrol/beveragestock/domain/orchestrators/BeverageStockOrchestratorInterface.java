package br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.orchestrators;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Beverage;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.request.RegisterBeverageRequest;

public interface BeverageStockOrchestratorInterface {
    Beverage registerBeverageAndHistory(RegisterBeverageRequest registerBeverageRequest);
}
