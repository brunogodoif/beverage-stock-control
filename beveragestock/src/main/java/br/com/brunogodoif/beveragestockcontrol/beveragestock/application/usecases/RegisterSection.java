package br.com.brunogodoif.beveragestockcontrol.beveragestock.application.usecases;


import br.com.brunogodoif.beveragestockcontrol.beveragestock.application.usecases.exceptions.SectionNotFoundException;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.application.gateways.SectionGateway;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Beverage;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Section;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.usecases.RegisterSectionInterface;
import org.springframework.stereotype.Service;

@Service
public class RegisterSection implements RegisterSectionInterface {


    private final SectionGateway sectionGateway;

    public RegisterSection(SectionGateway sectionGateway) {
        this.sectionGateway = sectionGateway;
    }

    @Override
    public Section registerBeverageInSection(Beverage beverage, Section section) {

        sectionGateway.findById(section.getId()).orElseThrow(() -> new SectionNotFoundException("Section not found"));
        section.addBeverage(beverage);
        Section savedSection = sectionGateway.save(section);
        return savedSection;
    }

    @Override
    public Section updateSection(Section section) {

        sectionGateway.findById(section.getId()).orElseThrow(() -> new SectionNotFoundException("Section not found"));
        Section save = sectionGateway.save(section);
        return save;
    }

}