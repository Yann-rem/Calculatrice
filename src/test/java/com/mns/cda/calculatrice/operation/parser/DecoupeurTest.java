package com.mns.cda.calculatrice.operation.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DecoupeurTest {

    private final Decoupeur decoupeur = new Decoupeur();

    @Test
    @DisplayName("Découpe avec un espace")
    void decoupeAvecUnEspace() {
        assertArrayEquals(new String[]{"3", "+", "2"}, decoupeur.decouper("3 + 2"));
    }

    @Test
    @DisplayName("Découpe avec plusieurs espaces")
    void decoupeAvecPlusieursEspaces() {
        assertArrayEquals(new String[]{"20", "*", "5"}, decoupeur.decouper("20   *   5"));
    }

    @Test
    @DisplayName("Découpe avec des espaces aux extrémités")
    void decoupeAvecEspacesAuxExtremites() {
        assertArrayEquals(new String[]{"7", "/", "2"}, decoupeur.decouper("  7 / 2  "));
    }

    @Test
    @DisplayName("Expression nulle lève une exception")
    void expressionNulle() {
        assertThrows(IllegalArgumentException.class, () -> decoupeur.decouper(null));
    }

    @Test
    @DisplayName("Expression vide lève une exception")
    void expressionVide() {
        assertThrows(IllegalArgumentException.class, () -> decoupeur.decouper(""));
    }

    @Test
    @DisplayName("Expression avec uniquement des espaces lève une exception")
    void expressionEspaces() {
        assertThrows(IllegalArgumentException.class, () -> decoupeur.decouper("   "));
    }

    @Test
    @DisplayName("Découpe avec trop de jetons")
    void decoupeTropDeJetons() {
        String[] jetons = decoupeur.decouper("1 + 2 + 3");
        assert jetons.length == 5;
    }

    @Test
    @DisplayName("Découpe sans espaces")
    void decoupeSansEspaces() {
        assertArrayEquals(new String[]{"1", "+", "1"}, decoupeur.decouper("1+1"));
    }

    @Test
    @DisplayName("Découpe sans espaces avec nombre négatif")
    void decoupeSansEspacesNombreNegatif() {
        assertArrayEquals(new String[]{"-3", "+", "2"}, decoupeur.decouper("-3+2"));
    }

    @Test
    @DisplayName("Découpe avec double opérateur donne trois jetons")
    void decoupeDoubleOperateur() {
        assertArrayEquals(new String[]{"1", "//", "1"}, decoupeur.decouper("1 // 1"));
    }
}
