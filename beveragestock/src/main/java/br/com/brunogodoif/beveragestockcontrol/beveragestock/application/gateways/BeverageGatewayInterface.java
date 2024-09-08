package br.com.brunogodoif.beveragestockcontrol.beveragestock.application.gateways;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Beverage;

import java.util.List;
import java.util.Optional;

public interface BeverageGatewayInterface {

    Beverage save(Beverage beverage);

    Optional<Beverage> findById(Long id);

    List<Beverage> findAllBySectionId(Long sectionId);
}