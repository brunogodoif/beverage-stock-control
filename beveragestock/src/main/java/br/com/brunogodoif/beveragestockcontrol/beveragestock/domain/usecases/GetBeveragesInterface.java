package br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.usecases;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Beverage;

import java.util.List;

public interface GetBeveragesInterface {

    List<Beverage> retrieveBeverages(Long sectionId);

    Beverage getBeverage(Long id);
}
