package br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.usecases;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.History;

public interface RegisterBeverageHistoryInterface {
    History registerHistory(History history);
}
