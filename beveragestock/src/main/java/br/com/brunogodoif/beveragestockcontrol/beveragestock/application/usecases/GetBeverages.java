package br.com.brunogodoif.beveragestockcontrol.beveragestock.application.usecases;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.application.usecases.exceptions.BeverageNotFoundException;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.application.usecases.exceptions.BeveragesNotFoundException;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.application.gateways.BeverageGatewayInterface;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Beverage;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.usecases.GetBeveragesInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetBeverages implements GetBeveragesInterface {

    private final BeverageGatewayInterface beverageGateway;

    public GetBeverages(BeverageGatewayInterface beverageGateway) {
        this.beverageGateway = beverageGateway;
    }

    @Override
    public List<Beverage> retrieveBeverages(Long sectionId) {

        List<Beverage> beverages = beverageGateway.findAllBySectionId(sectionId);
        if (beverages.isEmpty()) throw new BeveragesNotFoundException("No beverages found for the specified section.");
        return beverages;

    }

    @Override
    public Beverage getBeverage(Long id) {

        return beverageGateway.findById(id).orElseThrow(() -> new BeverageNotFoundException("Beverage not found"));

    }
}