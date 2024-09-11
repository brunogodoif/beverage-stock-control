package br.com.brunogodoif.beveragestockcontrol.beveragestock.application.usecases;


import br.com.brunogodoif.beveragestockcontrol.beveragestock.application.usecases.exceptions.BeverageNotFoundException;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.application.usecases.exceptions.SectionNotFoundException;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.application.gateways.BeverageGatewayInterface;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.application.gateways.SectionGateway;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Beverage;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Section;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.usecases.RegisterBeverageInSectionInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterBeverageInSection implements RegisterBeverageInSectionInterface {


    private final BeverageGatewayInterface beverageGateway;
    private final SectionGateway sectionGateway;

    public RegisterBeverageInSection(BeverageGatewayInterface beverageGateway, SectionGateway sectionGateway) {
        this.beverageGateway = beverageGateway;
        this.sectionGateway = sectionGateway;
    }

    @Override
    @Transactional
    public Beverage registerBeverage(Beverage beverage, Long sectionId) {

        Section section = sectionGateway.findById(sectionId)
                                        .orElseThrow(() -> new SectionNotFoundException("Section not found"));

        section.addBeverage(beverage);
        Section sectionUpdated = sectionGateway.save(section);

        return sectionUpdated.getBeverages().stream()
                           .filter(b -> b.getName().equals(beverage.getName()) && b.getEntryDate()
                                                                                   .equals(beverage.getEntryDate()))
                           .findFirst().orElseThrow(() -> new IllegalStateException("Beverage not found after saving"));
    }

    @Override
    public Beverage updateBeverage(Beverage beverage) {
        beverageGateway.findById(beverage.getId())
                                                   .orElseThrow(() -> new BeverageNotFoundException("Beverage not found"));

        sectionGateway.save(beverage.getSection());

        return beverageGateway.save(beverage);
    }
}