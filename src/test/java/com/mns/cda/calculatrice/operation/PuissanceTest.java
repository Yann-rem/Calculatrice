package com.mns.cda.calculatrice.operation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuissanceTest {

    private final Puissance puissance = new Puissance();

    @Test
    @DisplayName("Puissance avec exposant positif")
    void puissanceExposantPositif() {
        assertEquals(8.0, puissance.calculer(2, 3));
    }

    @Test
    @DisplayName("Puissance avec exposant décimal")
    void puissanceExposantDecimale() {
        assertEquals(4.0, puissance.calculer(16, 0.5));
    }

    @Test
    @DisplayName("Puissance avec exposant négatif")
    void puissanceExposantNegatif() {
        assertEquals(0.25, puissance.calculer(2, -2));
    }

    @Test
    @DisplayName("Puissance zéro")
    void puissanceZero() {
        assertEquals(1.0, puissance.calculer(5, 0));
    }

    @Test
    @DisplayName("Zéro puissance zéro")
    void zeroPuissanceZero() {
        assertEquals(1.0, puissance.calculer(0, 0));
    }
}
