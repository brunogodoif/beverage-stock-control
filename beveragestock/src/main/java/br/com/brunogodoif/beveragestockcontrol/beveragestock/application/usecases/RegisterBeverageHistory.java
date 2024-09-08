package br.com.brunogodoif.beveragestockcontrol.beveragestock.application.usecases;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.application.gateways.HistoryGateway;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.History;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.usecases.RegisterBeverageHistoryInterface;
import org.springframework.stereotype.Service;

@Service
public class RegisterBeverageHistory implements RegisterBeverageHistoryInterface {

    private final HistoryGateway historyGateway;

    public RegisterBeverageHistory(HistoryGateway historyGateway) {
        this.historyGateway = historyGateway;
    }

    @Override
    public History registerHistory(History history) {
        return historyGateway.save(history);
    }
}