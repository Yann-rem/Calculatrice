package com.mns.cda.calculatrice.operation;

import com.mns.cda.calculatrice.operation.exception.DivisionParZeroException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DivisionTest {

    private final Division division = new Division();

    @Test
    @DisplayName("Division avec des nombres positifs")
    void divisionNombresPositifs() {
        assertEquals(2, division.calculer(4, 2));
    }

    @Test
    @DisplayName("Division avec des nombres décimaux")
    void divisionNombresDecimaux() {
        assertEquals(2.25, division.calculer(4.5, 2.0));
    }

    @Test
    @DisplayName("Division avec un nombre négatif")
    void divisionNombreNegatif() {
        assertEquals(-3.0, division.calculer(-6, 2));
    }

    @Test
    @DisplayName("Division avec deux nombres négatifs")
    void divisionNombresNegatifs() {
        assertEquals(3.0, division.calculer(-6, -2));
    }

    @Test
    @DisplayName("Division de zéro par un nombre")
    void divisionDeZero() {
        assertEquals(0.0, division.calculer(0, 5));
    }

    @Test
    @DisplayName("Division par zéro lève DivisionParZeroException")
    void divisionParZero() {
        assertThrows(DivisionParZeroException.class, () -> division.calculer(10, 0));
    }
}
