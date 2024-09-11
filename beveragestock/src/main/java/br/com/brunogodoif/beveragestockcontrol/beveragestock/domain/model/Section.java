package br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.exceptions.InsufficientCapacityException;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.exceptions.InvalidSectionAttributeException;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.exceptions.SectionTypeConflictException;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
public class Section {

    private final Long id;
    private final String name;
    private final double totalCapacity;
    private final Set<Beverage> beverages;
    private final Set<History> histories;
    private double usedCapacity;


    public Section() {
        this.id = null;
        this.name = null;
        this.totalCapacity = 0;
        this.beverages = new HashSet<>();
        this.histories = new HashSet<>();
        this.usedCapacity = 0;
    }

    public Section(Long id, String name, double totalCapacity, double usedCapacity, Set<Beverage> beverages,
                   Set<History> histories
                  ) {
        validateId(id);
        validateName(name);
        validateTotalCapacity(totalCapacity);
        validateUsedCapacity(usedCapacity, totalCapacity);
        this.id = id;
        this.name = name;
        this.totalCapacity = totalCapacity;
        this.usedCapacity = usedCapacity;
        this.beverages = (beverages != null) ? beverages : new HashSet<>();
        this.histories = (histories != null) ? histories : new HashSet<>();
    }

    private void validateId(Long id) {
        if (id != null && id <= 0) {
            throw new InvalidSectionAttributeException("ID must be greater than zero.");
        }
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() > 100) {
            throw new InvalidSectionAttributeException("Name must be between 1 and 100 characters.");
        }
    }

    private void validateTotalCapacity(double totalCapacity) {
        if (totalCapacity <= 0) {
            throw new InvalidSectionAttributeException("Total capacity must be greater than zero.");
        }
    }

    private void validateUsedCapacity(double usedCapacity, double totalCapacity) {
        if (usedCapacity < 0 || usedCapacity > totalCapacity) {
            throw new InvalidSectionAttributeException("Used capacity must be between 0 and the total capacity.");
        }
    }

    public void addBeverage(Beverage beverage) {
        validateCapacity(beverage.getVolume());
        validateNoMixingOnSameDay(beverage);
        validateBeverageType(beverage);

        this.beverages.add(beverage);
        this.usedCapacity += beverage.getVolume();
        beverage.setSection(this);
    }

    private void validateCapacity(double volume) {
        if (!hasEnoughCapacity(volume)) {
            throw new InsufficientCapacityException("Insufficient capacity in the section");
        }
    }

    private void validateBeverageType(Beverage beverage) {

        if (this.usedCapacity == 0) {
            return;
        }

        boolean hasDifferentAlcoholType = beverages.stream()
                                                   .anyMatch(existingBeverage -> existingBeverage.getBeverageType()
                                                                                                 .isAlcoholic() != beverage.getBeverageType()
                                                                                                                           .isAlcoholic());

        if (hasDifferentAlcoholType) {
            throw new SectionTypeConflictException("Section cannot store both alcoholic and non-alcoholic beverages.");
        }
    }

    private void validateNoMixingOnSameDay(Beverage beverage) {
        boolean isAlcoholic = beverage.getBeverageType().isAlcoholic();

        if (this.usedCapacity == 0) {
            return;
        }

        boolean nonAlcoholicToday = hasNonAlcoholicBeveragesToday();
        boolean alcoholicToday = hasAlcoholicBeveragesToday();

        if (isAlcoholic && nonAlcoholicToday) {
            throw new SectionTypeConflictException(
                    "Cannot add alcoholic beverages when non-alcoholic beverages were added on the same day and the section is not empty.");
        }

        if (!isAlcoholic && alcoholicToday) {
            throw new SectionTypeConflictException(
                    "Cannot add non-alcoholic beverages when alcoholic beverages were added on the same day and the section is not empty.");
        }

    }

    private boolean hasAlcoholicBeveragesToday() {
        return beverages.stream().anyMatch(beverage -> beverage.getBeverageType()
                                                               .isAlcoholic() && isToday(beverage.getEntryDate()));
    }

    private boolean hasNonAlcoholicBeveragesToday() {
        return beverages.stream().anyMatch(beverage -> !beverage.getBeverageType()
                                                                .isAlcoholic() && isToday(beverage.getEntryDate()));
    }

    private boolean isToday(LocalDateTime dateTime) {
        return dateTime.toLocalDate().isEqual(LocalDateTime.now().toLocalDate());
    }

    public boolean hasEnoughCapacity(double volume) {
        return (this.totalCapacity - this.usedCapacity) >= volume;
    }


    public void increaseUsedCapacity(double volume) {
        if ((this.usedCapacity + volume) > this.totalCapacity) {
            throw new IllegalArgumentException("The added volume exceeds the total capacity of the section.");
        }
        this.usedCapacity += volume;
    }

    public void decreaseUsedCapacity(double volume) {
        if (this.usedCapacity < volume) {
            throw new IllegalArgumentException("Reduction volume cannot be greater than the used capacity.");
        }
        this.usedCapacity -= volume;
    }

}
