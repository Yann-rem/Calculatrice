package com.mns.cda.calculatrice.operation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SoustractionTest {

    private final Soustraction soustraction = new Soustraction();

    @Test
    @DisplayName("Soustraction avec des nombres positifs")
    void soustractionNombresPositifs() {
        assertEquals(2.0, soustraction.calculer(5, 3));
    }

    @Test
    @DisplayName("Soustraction avec des nombres décimaux")
    void soustractionNombresDecimaux() {
        assertEquals(1.5, soustraction.calculer(3.5, 2.0));
    }

    @Test
    @DisplayName("Soustraction avec des nombres négatifs")
    void soustractionNombresNegatifs() {
        assertEquals(-5.0, soustraction.calculer(-3, 2));
    }

    @Test
    @DisplayName("Soustraction avec zéro")
    void soustractionAvecZero() {
        assertEquals(7.0, soustraction.calculer(7, 0));
    }

    @Test
    @DisplayName("Soustraction donnant un résultat négatif")
    void soustractionResultatNegatif() {
        assertEquals(-3.0, soustraction.calculer(2, 5));
    }
}
