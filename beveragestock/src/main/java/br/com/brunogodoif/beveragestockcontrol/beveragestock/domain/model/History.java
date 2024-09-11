package br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.exceptions.InvalidHistoryAttributeException;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class History {

    private final OperationType operationType;
    private final double volume;
    private final LocalDateTime date;
    private final String responsible;
    private final Section section;
    private final Beverage beverage;
    private Long id;

    public History() {
        this.id = null;
        this.beverage = null;
        this.section = null;
        this.responsible = null;
        this.date = null;
        this.volume = 0;
        this.operationType = null;
    }

    public History(Long id, OperationType operationType, double volume, LocalDateTime date, String responsible,
                   Section section, Beverage beverage
                  ) {

        validateId(id);
        validateOperationType(operationType);
        validateVolume(volume);
        validateDate(date);
        validateResponsible(responsible);
        validateSection(section);
        validateBeverage(beverage);
        this.id = id;
        this.operationType = operationType;
        this.volume = volume;
        this.date = date;
        this.responsible = responsible;
        this.section = section;
        this.beverage = beverage;
    }

    public History(Beverage beverage, Section section, String responsible, LocalDateTime date, double volume,
                   OperationType operationType
                  ) {
        validateOperationType(operationType);
        validateVolume(volume);
        validateDate(date);
        validateResponsible(responsible);
        validateSection(section);
        validateBeverage(beverage);
        this.beverage = beverage;
        this.section = section;
        this.responsible = responsible;
        this.date = date;
        this.volume = volume;
        this.operationType = operationType;
    }

    private void validateId(Long id) {
        if (id != null && id <= 0) {
            throw new InvalidHistoryAttributeException("ID must be greater than zero.");
        }
    }

    private void validateOperationType(OperationType operationType) {
        if (operationType == null) {
            throw new InvalidHistoryAttributeException("Operation type cannot be null.");
        }
    }

    private void validateVolume(double volume) {
        if (volume <= 0) {
            throw new InvalidHistoryAttributeException("Volume must be greater than zero.");
        }
    }

    private void validateDate(LocalDateTime date) {
        if (date == null) {
            throw new InvalidHistoryAttributeException("Date cannot be null.");
        }
        if (date.isAfter(LocalDateTime.now())) {
            throw new InvalidHistoryAttributeException("Date cannot be in the future.");
        }
    }

    private void validateResponsible(String responsible) {
        if (responsible == null || responsible.trim().isEmpty() || responsible.length() > 100) {
            throw new InvalidHistoryAttributeException("Responsible must be between 1 and 100 characters.");
        }
    }

    private void validateSection(Section section) {
        if (section == null) {
            throw new InvalidHistoryAttributeException("Section cannot be null.");
        }
    }

    private void validateBeverage(Beverage beverage) {
        if (beverage == null) {
            throw new InvalidHistoryAttributeException("Beverage cannot be null.");
        }
    }

}
