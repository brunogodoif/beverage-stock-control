package br.com.brunogodoif.beveragestockcontrol.beveragestock.application.orchestrators;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.application.usecases.exceptions.BeverageTypeNotFoundException;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.application.gateways.BeverageTypeGateway;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Beverage;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.BeverageType;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.History;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.OperationType;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.orchestrators.BeverageStockOrchestratorInterface;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.usecases.RegisterBeverageHistoryInterface;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.usecases.RegisterBeverageInSectionInterface;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.request.RegisterBeverageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BeverageStockOrchestrator implements BeverageStockOrchestratorInterface {

    private final BeverageTypeGateway beverageTypeGateway;
    private final RegisterBeverageInSectionInterface registerBeverageInSection;
    private final RegisterBeverageHistoryInterface registerBeverageHistoryUseCase;

    public BeverageStockOrchestrator(BeverageTypeGateway beverageTypeGateway,
                                     RegisterBeverageInSectionInterface registerBeverageInSection,
                                     RegisterBeverageHistoryInterface registerBeverageHistoryInterface
                                    ) {
        this.beverageTypeGateway = beverageTypeGateway;
        this.registerBeverageInSection = registerBeverageInSection;
        this.registerBeverageHistoryUseCase = registerBeverageHistoryInterface;
    }

    @Transactional
    public Beverage  registerBeverageAndHistory(RegisterBeverageRequest registerBeverageRequest) {

        BeverageType beverageType = beverageTypeGateway.findById(registerBeverageRequest.beverageTypeId().longValue())
                                                       .orElseThrow(() -> new BeverageTypeNotFoundException(
                                                               "BeverageType not found"));

        Beverage beverage = new Beverage(registerBeverageRequest.name(),
                                         beverageType,
                                         registerBeverageRequest.volume(),
                                         LocalDateTime.now());

        Beverage beverageSaved = registerBeverageInSection.registerBeverage(beverage,
                                                                            Integer.toUnsignedLong(
                                                                                    registerBeverageRequest.sectionId()));

        History history = new History(null,
                                      OperationType.ENTRY,
                                      registerBeverageRequest.volume(),
                                      LocalDateTime.now(),
                                      registerBeverageRequest.responsible(),
                                      beverageSaved.getSection(),
                                      beverageSaved);

        registerBeverageHistoryUseCase.registerHistory(history);
        return beverageSaved;
    }

}
