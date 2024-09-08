package br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.usecases;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Beverage;

public interface RegisterBeverageInSectionInterface {

    Beverage registerBeverage(Beverage beverage, Long sectionId);

    Beverage updateBeverage(Beverage beverage);
}
