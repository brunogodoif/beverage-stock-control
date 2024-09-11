package br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.exceptions.InvalidHistoryAttributeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

class HistoryTest {

    private Section section;
    private Beverage beverage;

    @BeforeEach
    void setUp() {
        section = new Section(1L, "Seção 1", 100, 0.0, Set.of(), Set.of());
        beverage = new Beverage("Guaraná", new BeverageType(1L, "Refrigerante", false), 10, LocalDateTime.now());
    }

    @Test
    void testValidHistoryCreationWithId() {
        History history = new History(1L, OperationType.ENTRY, 10.0, LocalDateTime.now(), "João Silva", section, beverage);

        Assertions.assertEquals(OperationType.ENTRY, history.getOperationType());
        Assertions.assertEquals(10.0, history.getVolume());
        Assertions.assertNotNull(history.getDate());
        Assertions.assertEquals("João Silva", history.getResponsible());
        Assertions.assertEquals(section, history.getSection());
        Assertions.assertEquals(beverage, history.getBeverage());
    }

    @Test
    void testValidHistoryCreationWithoutId() {
        History history = new History(beverage, section, "João Silva", LocalDateTime.now(), 10.0, OperationType.ENTRY);

        Assertions.assertEquals(OperationType.ENTRY, history.getOperationType());
        Assertions.assertEquals(10.0, history.getVolume());
        Assertions.assertNotNull(history.getDate());
        Assertions.assertEquals("João Silva", history.getResponsible());
        Assertions.assertEquals(section, history.getSection());
        Assertions.assertEquals(beverage, history.getBeverage());
    }

    @Test
    void testNullOperationTypeThrowsException() {
        Exception exception = Assertions.assertThrows(InvalidHistoryAttributeException.class, () -> {
            new History(1L, null, 10.0, LocalDateTime.now(), "João Silva", section, beverage);
        });
        Assertions.assertEquals("Operation type cannot be null.", exception.getMessage());
    }

    @Test
    void testInvalidVolumeThrowsException() {
        Exception exception = Assertions.assertThrows(InvalidHistoryAttributeException.class, () -> {
            new History(1L, OperationType.ENTRY, -1.0, LocalDateTime.now(), "João Silva", section, beverage);
        });
        Assertions.assertEquals("Volume must be greater than zero.", exception.getMessage());
    }

    @Test
    void testNullDateThrowsException() {
        Exception exception = Assertions.assertThrows(InvalidHistoryAttributeException.class, () -> {
            new History(1L, OperationType.ENTRY, 10.0, null, "João Silva", section, beverage);
        });
        Assertions.assertEquals("Date cannot be null.", exception.getMessage());
    }

    @Test
    void testFutureDateThrowsException() {
        Exception exception = Assertions.assertThrows(InvalidHistoryAttributeException.class, () -> {
            new History(1L, OperationType.ENTRY, 10.0, LocalDateTime.now().plusDays(1), "João Silva", section, beverage);
        });
        Assertions.assertEquals("Date cannot be in the future.", exception.getMessage());
    }

    @Test
    void testInvalidResponsibleThrowsException() {
        Exception exception = Assertions.assertThrows(InvalidHistoryAttributeException.class, () -> {
            new History(1L, OperationType.ENTRY, 10.0, LocalDateTime.now(), "", section, beverage);
        });
        Assertions.assertEquals("Responsible must be between 1 and 100 characters.", exception.getMessage());
    }

    @Test
    void testNullSectionThrowsException() {
        Exception exception = Assertions.assertThrows(InvalidHistoryAttributeException.class, () -> {
            new History(1L, OperationType.ENTRY, 10.0, LocalDateTime.now(), "João Silva", null, beverage);
        });
        Assertions.assertEquals("Section cannot be null.", exception.getMessage());
    }

    @Test
    void testNullBeverageThrowsException() {
        Exception exception = Assertions.assertThrows(InvalidHistoryAttributeException.class, () -> {
            new History(1L, OperationType.ENTRY, 10.0, LocalDateTime.now(), "João Silva", section, null);
        });
        Assertions.assertEquals("Beverage cannot be null.", exception.getMessage());
    }
}
