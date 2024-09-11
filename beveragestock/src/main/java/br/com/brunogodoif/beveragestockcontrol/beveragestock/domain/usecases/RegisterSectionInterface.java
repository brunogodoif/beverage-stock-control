package br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.usecases;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Beverage;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Section;

public interface RegisterSectionInterface {

    Section registerBeverageInSection(Beverage beverage, Section section);

    Section updateSection(Section section);
}
