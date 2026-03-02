package com.mns.cda.calculatrice.operation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdditionTest {

    private final Addition addition = new Addition();

    @Test
    @DisplayName("Addition avec des nombres positifs")
    void additionNombresPositifs() {
        assertEquals(5.0, addition.calculer(2, 3));
    }

    @Test
    @DisplayName("Addition avec des nombres décimaux")
    void additionNombresDecimaux() {
        assertEquals(3.5, addition.calculer(1.5, 2.0));
    }

    @Test
    @DisplayName("Addition avec des nombres négatifs")
    void additionNombresNegatifs() {
        assertEquals(-1.0, addition.calculer(-3, 2));
    }

    @Test
    @DisplayName("Addition avec zéro")
    void additionAvecZero() {
        assertEquals(7.0, addition.calculer(7, 0));
    }
}
