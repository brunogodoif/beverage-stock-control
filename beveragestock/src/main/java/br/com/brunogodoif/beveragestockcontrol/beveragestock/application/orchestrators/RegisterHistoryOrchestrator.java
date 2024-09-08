package br.com.brunogodoif.beveragestockcontrol.beveragestock.application.orchestrators;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.application.orchestrators.exceptions.InvalidOperationTypeException;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Beverage;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.History;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.OperationType;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.orchestrators.RegisterHistoryOrchestratorInterface;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.usecases.GetBeveragesInterface;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.usecases.RegisterBeverageHistoryInterface;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.usecases.RegisterBeverageInSectionInterface;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.usecases.RegisterSectionInterface;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.request.RegisterHistoryRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RegisterHistoryOrchestrator implements RegisterHistoryOrchestratorInterface {

    private final RegisterSectionInterface registerSection;
    private final RegisterBeverageHistoryInterface registerBeverageHistoryInterface;
    private final RegisterBeverageInSectionInterface registerBeverageInSection;
    private final GetBeveragesInterface getBeveragesInterface;

    public RegisterHistoryOrchestrator(RegisterSectionInterface registerSection,
                                       RegisterBeverageHistoryInterface registerBeverageHistoryInterface,
                                       RegisterBeverageInSectionInterface registerBeverageInSection,
                                       GetBeveragesInterface getBeveragesInterface
                                      ) {
        this.registerSection = registerSection;
        this.registerBeverageHistoryInterface = registerBeverageHistoryInterface;
        this.registerBeverageInSection = registerBeverageInSection;
        this.getBeveragesInterface = getBeveragesInterface;
    }

    @Override
    @Transactional
    public History registerHistory(RegisterHistoryRequest request) {

        OperationType operationType = getOperationType(request.operationType());
        Beverage beverage = getBeveragesInterface.getBeverage(request.beverage().id().longValue());

        if (operationType == OperationType.ENTRY) {
            return processOperation(beverage, request.beverage().volume(), request.responsible(), OperationType.ENTRY);
        } else if (operationType == OperationType.EXIT) {
            return processOperation(beverage, request.beverage().volume(), request.responsible(), OperationType.EXIT);
        } else {
            throw new InvalidOperationTypeException("Invalid operation type: " + request.operationType());
        }
    }

    private OperationType getOperationType(String operationTypeString) {
        try {
            return OperationType.valueOf(operationTypeString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidOperationTypeException("Invalid operation type: " + operationTypeString);
        }
    }

    private History processOperation(Beverage beverage, double volume, String responsible, OperationType operationType
                                    ) {
        if (operationType == OperationType.ENTRY) {
            beverage.increaseVolume(volume);
            beverage.getSection().increaseUsedCapacity(volume);
        } else if (operationType == OperationType.EXIT) {
            beverage.decreaseVolume(volume);
            beverage.getSection().decreaseUsedCapacity(volume);
        }

        Beverage updatedBeverage = registerBeverageInSection.updateBeverage(beverage);
        registerSection.updateSection(updatedBeverage.getSection());

        History history = createHistory(updatedBeverage, volume, responsible, operationType);
        return registerBeverageHistoryInterface.registerHistory(history);
    }


    private History createHistory(Beverage beverage, double volume, String responsible, OperationType operationType) {
        return new History(beverage, beverage.getSection(), responsible, LocalDateTime.now(), volume, operationType);
    }
}