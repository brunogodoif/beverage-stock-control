package br.com.brunogodoif.beveragestockcontrol.beveragestock.application.gateways;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.BeverageType;

import java.util.List;
import java.util.Optional;

public interface BeverageTypeGateway {

    BeverageType save(BeverageType beverageType);

    Optional<BeverageType> findById(Long id);

}