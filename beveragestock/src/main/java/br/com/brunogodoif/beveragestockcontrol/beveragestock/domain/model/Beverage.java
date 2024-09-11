package br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.exceptions.InvalidBeverageAttributeException;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Beverage {

    private final String name;
    private final BeverageType beverageType;
    private final LocalDateTime entryDate;
    private Long id;
    private double volume;
    private Section section;

    public Beverage() {
        this.name = null;
        this.beverageType = null;
        this.entryDate = null;
        this.id = null;
        this.volume = 0;
        this.section = null;
    }

    public Beverage(Long id, String name, BeverageType beverageType, double volume, LocalDateTime entryDate,
                    Section section
                   ) {
        validateId(id);
        validateName(name);
        validateBeverageType(beverageType);
        validateVolume(volume);
        validateEntryDate(entryDate);
        this.id = id;
        this.name = name;
        this.beverageType = beverageType;
        this.volume = volume;
        this.entryDate = entryDate;
        this.section = section;
    }

    public Beverage(String name, BeverageType beverageType, double volume, LocalDateTime entryDate) {
        validateName(name);
        validateVolume(volume);
        validateEntryDate(entryDate);
        this.name = name;
        this.beverageType = beverageType;
        this.volume = volume;
        this.entryDate = entryDate;
    }

    private void validateId(Long id) {
        if (id != null && id <= 0) {
            throw new InvalidBeverageAttributeException("ID must be greater than zero.");
        }
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() > 100) {
            throw new InvalidBeverageAttributeException("Name must be between 1 and 100 characters.");
        }
    }

    private void validateBeverageType(BeverageType beverageType) {
        if (beverageType == null) {
            throw new InvalidBeverageAttributeException("Beverage type cannot be null.");
        }
    }

    private void validateVolume(double volume) {
        if (volume < 0) {
            throw new InvalidBeverageAttributeException("Volume must be greater than zero.");
        }
    }

    private void validateEntryDate(LocalDateTime entryDate) {
        if (entryDate == null) {
            throw new InvalidBeverageAttributeException("Entry date cannot be null.");
        }
        if (entryDate.isAfter(LocalDateTime.now())) {
            throw new InvalidBeverageAttributeException("Entry date cannot be in the future.");
        }
    }

    public void increaseVolume(double additionalVolume) {
        if (additionalVolume <= 0) {
            throw new IllegalArgumentException("Additional volume must be greater than zero.");
        }
        this.volume += additionalVolume;
    }

    public void decreaseVolume(double reductionVolume) {
        if (reductionVolume <= 0) {
            throw new IllegalArgumentException("Reduction volume must be greater than zero.");
        }
        if (this.volume < reductionVolume) {
            throw new IllegalArgumentException("Reduction volume cannot be greater than the current volume.");
        }
        this.volume -= reductionVolume;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beverage beverage = (Beverage) o;
        return Double.compare(beverage.volume, volume) == 0 && Objects.equals(id, beverage.id) && Objects.equals(name,
                                                                                                                 beverage.name) && Objects.equals(
                beverageType,
                beverage.beverageType) && Objects.equals(entryDate, beverage.entryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, beverageType, volume, entryDate);
    }

}
