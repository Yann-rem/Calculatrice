package com.mns.cda.calculatrice.operation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiplicationTest {

    private final Multiplication multiplication = new Multiplication();

    @Test
    @DisplayName("Multiplication avec des nombres positifs")
    void multiplicationNombresPositifs() {
        assertEquals(6.0, multiplication.calculer(3, 2));
    }

    @Test
    @DisplayName("Multiplication avec des nombres décimaux")
    void multiplicationNombresDecimaux() {
        assertEquals(5.25, multiplication.calculer(1.5, 3.5));
    }

    @Test
    @DisplayName("Multiplication avec un nombre négatif")
    void multiplicationNombreNegatif() {
        assertEquals(-6.0, multiplication.calculer(-3, 2));
    }

    @Test
    @DisplayName("Multiplication de deux nombres négatifs")
    void multiplicationNombresNegatifs() {
        assertEquals(6.0, multiplication.calculer(-3, -2));
    }

    @Test
    @DisplayName("Multiplication par zéro")
    void multiplicationParZero() {
        assertEquals(0.0, multiplication.calculer(7, 0));
    }
}