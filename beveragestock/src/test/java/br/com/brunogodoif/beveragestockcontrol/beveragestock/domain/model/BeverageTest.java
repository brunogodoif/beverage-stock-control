package br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.exceptions.InvalidBeverageAttributeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;

class BeverageTest {

    private Section section;

    @BeforeEach
    void setUp() {
        section = new Section(1L, "Seção de Bebidas", 500, 0, new HashSet<>(), new HashSet<>());
    }

    @Test
    void testBeverageCreationWithValidData() {
        BeverageType refrigerante = new BeverageType(1L, "Refrigerante", false);
        Beverage bebida = new Beverage(1L, "Guaraná", refrigerante, 100, LocalDateTime.now(), section);

        Assertions.assertEquals("Guaraná", bebida.getName());
        Assertions.assertEquals(refrigerante, bebida.getBeverageType());
        Assertions.assertEquals(100, bebida.getVolume());
        Assertions.assertEquals(section, bebida.getSection());
        Assertions.assertNotNull(bebida.getEntryDate());
    }

    @Test
    void testBeverageCreationWithoutId() {
        BeverageType refrigerante = new BeverageType(1L, "Refrigerante", false);
        Beverage bebida = new Beverage("Guaraná", refrigerante, 100, LocalDateTime.now());

        Assertions.assertEquals("Guaraná", bebida.getName());
        Assertions.assertEquals(refrigerante, bebida.getBeverageType());
        Assertions.assertEquals(100, bebida.getVolume());
        Assertions.assertNotNull(bebida.getEntryDate());
    }

    @Test
    void testBeverageCreationWithInvalidIdThrowsException() {
        BeverageType refrigerante = new BeverageType(1L, "Refrigerante", false);
        Exception exception = Assertions.assertThrows(InvalidBeverageAttributeException.class, () -> {
            new Beverage(-1L, "Guaraná", refrigerante, 100, LocalDateTime.now(), section);
        });

        Assertions.assertEquals("ID must be greater than zero.", exception.getMessage());
    }

    @Test
    void testBeverageCreationWithEmptyNameThrowsException() {
        BeverageType refrigerante = new BeverageType(1L, "Refrigerante", false);
        Exception exception = Assertions.assertThrows(InvalidBeverageAttributeException.class, () -> {
            new Beverage(1L, "", refrigerante, 100, LocalDateTime.now(), section);
        });

        Assertions.assertEquals("Name must be between 1 and 100 characters.", exception.getMessage());
    }

    @Test
    void testBeverageCreationWithNullBeverageTypeThrowsException() {
        Exception exception = Assertions.assertThrows(InvalidBeverageAttributeException.class, () -> {
            new Beverage(1L, "Guaraná", null, 100, LocalDateTime.now(), section);
        });

        Assertions.assertEquals("Beverage type cannot be null.", exception.getMessage());
    }

    @Test
    void testBeverageCreationWithInvalidVolumeThrowsException() {
        BeverageType refrigerante = new BeverageType(1L, "Refrigerante", false);
        Exception exception = Assertions.assertThrows(InvalidBeverageAttributeException.class, () -> {
            new Beverage(1L, "Guaraná", refrigerante, -100, LocalDateTime.now(), section);
        });

        Assertions.assertEquals("Volume must be greater than zero.", exception.getMessage());
    }

    @Test
    void testBeverageCreationWithNullEntryDateThrowsException() {
        BeverageType refrigerante = new BeverageType(1L, "Refrigerante", false);
        Exception exception = Assertions.assertThrows(InvalidBeverageAttributeException.class, () -> {
            new Beverage(1L, "Guaraná", refrigerante, 100, null, section);
        });

        Assertions.assertEquals("Entry date cannot be null.", exception.getMessage());
    }

    @Test
    void testBeverageCreationWithFutureEntryDateThrowsException() {
        BeverageType refrigerante = new BeverageType(1L, "Refrigerante", false);
        Exception exception = Assertions.assertThrows(InvalidBeverageAttributeException.class, () -> {
            new Beverage(1L, "Guaraná", refrigerante, 100, LocalDateTime.now().plusDays(1), section);
        });

        Assertions.assertEquals("Entry date cannot be in the future.", exception.getMessage());
    }

    @Test
    void testIncreaseVolumeSuccess() {
        BeverageType refrigerante = new BeverageType(1L, "Refrigerante", false);
        Beverage bebida = new Beverage(1L, "Guaraná", refrigerante, 100, LocalDateTime.now(), section);

        bebida.increaseVolume(50);
        Assertions.assertEquals(150, bebida.getVolume());
    }

    @Test
    void testIncreaseVolumeWithInvalidValueThrowsException() {
        BeverageType refrigerante = new BeverageType(1L, "Refrigerante", false);
        Beverage bebida = new Beverage(1L, "Guaraná", refrigerante, 100, LocalDateTime.now(), section);

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bebida.increaseVolume(-50);
        });

        Assertions.assertEquals("Additional volume must be greater than zero.", exception.getMessage());
    }

    @Test
    void testDecreaseVolumeSuccess() {
        BeverageType refrigerante = new BeverageType(1L, "Refrigerante", false);
        Beverage bebida = new Beverage(1L, "Guaraná", refrigerante, 100, LocalDateTime.now(), section);

        bebida.decreaseVolume(50);
        Assertions.assertEquals(50, bebida.getVolume());
    }

    @Test
    void testDecreaseVolumeWithInvalidValueThrowsException() {
        BeverageType refrigerante = new BeverageType(1L, "Refrigerante", false);
        Beverage bebida = new Beverage(1L, "Guaraná", refrigerante, 100, LocalDateTime.now(), section);

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bebida.decreaseVolume(150);
        });

        Assertions.assertEquals("Reduction volume cannot be greater than the current volume.", exception.getMessage());
    }

    @Test
    void testSetSectionSuccess() {
        BeverageType refrigerante = new BeverageType(1L, "Refrigerante", false);
        Beverage bebida =new Beverage(1L, "Guaraná", refrigerante, 100, LocalDateTime.now(), section);

        bebida.setSection(section);
        Assertions.assertEquals(section, bebida.getSection());
    }

    @Test
    void testEqualsAndHashCode() {
        BeverageType refrigerante = new BeverageType(1L, "Refrigerante", false);
        Beverage bebida1 = new Beverage(1L, "Guaraná", refrigerante, 100, LocalDateTime.now(), section);
        Beverage bebida2 = new Beverage(1L, "Guaraná", refrigerante, 100, LocalDateTime.now(), section);

        Assertions.assertEquals(bebida1, bebida2);
        Assertions.assertEquals(bebida1.hashCode(), bebida2.hashCode());
    }
}
