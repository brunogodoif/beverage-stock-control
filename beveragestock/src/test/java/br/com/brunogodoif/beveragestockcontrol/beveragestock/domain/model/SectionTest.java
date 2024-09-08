package br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.exceptions.InsufficientCapacityException;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.exceptions.InvalidSectionAttributeException;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.exceptions.SectionTypeConflictException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class SectionTest {
    private Section section;

    @BeforeEach
    void setup() {
        section = new Section(1L, "Seção 1", 100.0, 0.0, new HashSet<>(), new HashSet<>());
    }

    @Test
    void shouldCreateSectionSuccessfully() {
        Section validSection = new Section(1L, "Seção 1", 100.0, 0.0, new HashSet<>(), new HashSet<>());
        assertNotNull(validSection);
        assertEquals(1L, validSection.getId());
        assertEquals("Seção 1", validSection.getName());
        assertEquals(100.0, validSection.getTotalCapacity());
        assertEquals(0.0, validSection.getUsedCapacity());
        assertTrue(validSection.getBeverages().isEmpty());
    }


    @Test
    void shouldThrowExceptionWhenIdIsInvalid() {
        assertThrows(InvalidSectionAttributeException.class,
                     () -> new Section(0L, "Seção 1", 100.0, 0.0, new HashSet<>(), new HashSet<>()));
    }

    @Test
    void shouldThrowExceptionWhenNameIsInvalid() {
        assertThrows(InvalidSectionAttributeException.class,
                     () -> new Section(1L, "", 100.0, 0.0, new HashSet<>(), new HashSet<>()));
    }

    @Test
    void shouldThrowExceptionWhenTotalCapacityIsInvalid() {
        assertThrows(InvalidSectionAttributeException.class,
                     () -> new Section(1L, "Seção 1", -100.0, 0.0, new HashSet<>(), new HashSet<>()));
    }

    @Test
    void shouldThrowExceptionWhenUsedCapacityExceedsTotalCapacity() {
        assertThrows(InvalidSectionAttributeException.class,
                     () -> new Section(1L, "Seção 1", 100.0, 120.0, new HashSet<>(), new HashSet<>()));
    }

    @Test
    void shouldAddBeverageSuccessfullyWhenThereIsEnoughCapacity() {
        Beverage beverage = new Beverage("Cerveja", new BeverageType(1L, "Cerveja", true), 50.0, LocalDateTime.now());
        assertDoesNotThrow(() -> section.addBeverage(beverage));
    }

    @Test
    void shouldThrowExceptionWhenAddingBeverageExceedsCapacity() {
        Beverage beverage = new Beverage("Cerveja", new BeverageType(1L, "Cerveja", true), 150.0, LocalDateTime.now());
        assertThrows(InsufficientCapacityException.class, () -> section.addBeverage(beverage));
    }

    @Test
    void shouldThrowExceptionWhenMixingAlcoholicAndNonAlcoholicBeverages() {
        Beverage alcoholicBeverage = new Beverage("Cerveja",
                                                  new BeverageType(1L, "Cerveja", true),
                                                  50.0,
                                                  LocalDateTime.now());
        section.addBeverage(alcoholicBeverage);

        Beverage nonAlcoholicBeverage = new Beverage("Suco",
                                                     new BeverageType(2L, "Suco", false),
                                                     50.0,
                                                     LocalDateTime.now());
        assertThrows(SectionTypeConflictException.class, () -> section.addBeverage(nonAlcoholicBeverage));
    }
}