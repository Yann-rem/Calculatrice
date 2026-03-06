package com.mns.cda.calculatrice.operation;

import com.mns.cda.calculatrice.operation.exception.DivisionParZeroException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ModuloTest {

    private final Modulo modulo = new Modulo();

    @Test
    @DisplayName("Modulo avec des nombres positifs")
    void moduloNombresPositifs() {
        assertEquals(1.0, modulo.calculer(10, 3));
    }

    @Test
    @DisplayName("Modulo avec des nombres décimaux")
    void moduloNombresDecimaux() {
        assertEquals(0.5, modulo.calculer(5.5, 2.5));
    }

    @Test
    @DisplayName("Modulo avec un nombre négatif")
    void moduloNombreNegatif() {
        assertEquals(-1.0, modulo.calculer(-10, 3));
    }

    @Test
    @DisplayName("Modulo avec des nombres négatifs")
    void moduloNombresNegatifs() {
        assertEquals(-1.0, modulo.calculer(-10, -3));
    }

    @Test
    @DisplayName("Modulo de zéro")
    void moduloDeZero() {
        assertEquals(0.0, modulo.calculer(0, 5));
    }

    @Test
    @DisplayName("Modulo par zéro lève DivisionParZeroException")
    void moduloParZero() {
        assertThrows(DivisionParZeroException.class, () -> modulo.calculer(10, 0));
    }
}
