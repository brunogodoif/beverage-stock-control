package br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.exceptions.InvalidBeverageTypeAttributeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BeverageTypeTest {

    @Test
    void testValidBeverageTypeCreation() {
        BeverageType beverageType = new BeverageType(1L, "Cerveja", true);
        Assertions.assertEquals(1L, beverageType.getId());
        Assertions.assertEquals("Cerveja", beverageType.getName());
        Assertions.assertTrue(beverageType.isAlcoholic());
    }

    @Test
    void testInvalidIdThrowsException() {
        Exception exception = Assertions.assertThrows(InvalidBeverageTypeAttributeException.class, () -> {
            new BeverageType(0L, "Vinho", true);
        });
        Assertions.assertEquals("ID must be greater than zero.", exception.getMessage());
    }

    @Test
    void testInvalidNameThrowsException() {
        Exception exception = Assertions.assertThrows(InvalidBeverageTypeAttributeException.class, () -> {
            new BeverageType(1L, "", false);
        });
        Assertions.assertEquals("Name must be between 1 and 100 characters.", exception.getMessage());
    }

    @Test
    void testNullNameThrowsException() {
        Exception exception = Assertions.assertThrows(InvalidBeverageTypeAttributeException.class, () -> {
            new BeverageType(1L, null, true);
        });
        Assertions.assertEquals("Name must be between 1 and 100 characters.", exception.getMessage());
    }

    @Test
    void testExceedingMaxNameLengthThrowsException() {
        String longName = "a".repeat(101);
        Exception exception = Assertions.assertThrows(InvalidBeverageTypeAttributeException.class, () -> {
            new BeverageType(1L, longName, false);
        });
        Assertions.assertEquals("Name must be between 1 and 100 characters.", exception.getMessage());
    }

}