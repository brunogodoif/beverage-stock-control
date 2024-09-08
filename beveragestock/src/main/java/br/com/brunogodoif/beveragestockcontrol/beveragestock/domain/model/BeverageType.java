package br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.exceptions.InvalidBeverageTypeAttributeException;
import lombok.Getter;

import java.util.Objects;


@Getter
public class BeverageType {

    private final Long id;
    private final String name;
    private final boolean alcoholic;

    public BeverageType() {
        this.id = null;
        this.name = null;
        this.alcoholic = false;
    }

    public BeverageType(Long id, String name, boolean alcoholic) {
        validateId(id);
        validateName(name);
        this.id = id;
        this.name = name;
        this.alcoholic = alcoholic;
    }

    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidBeverageTypeAttributeException("ID must be greater than zero.");
        }
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() > 100) {
            throw new InvalidBeverageTypeAttributeException("Name must be between 1 and 100 characters.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeverageType that = (BeverageType) o;
        return alcoholic == that.alcoholic && Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, alcoholic);
    }

}
