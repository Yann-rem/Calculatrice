package com.mns.cda.calculatrice.operation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OperationRegistryTest {

    private OperationRegistry registre;

    @BeforeEach
    void setUp() {
        registre = new OperationRegistry();
        registre.enregistrer(new Addition());
        registre.enregistrer(new Soustraction());
        registre.enregistrer(new Multiplication());
        registre.enregistrer(new Division());
    }

    @Test
    @DisplayName("Récupérer l'addition par son symbole")
    void recupererAddition() {
        IOperation operation = registre.recuperer("+");
        assertInstanceOf(Addition.class, operation);
    }

    @Test
    @DisplayName("Récupérer la soustraction par son symbole")
    void recupererSoustraction() {
        IOperation operation = registre.recuperer("-");
        assertInstanceOf(Soustraction.class, operation);
    }

    @Test
    @DisplayName("Récupérer la multiplication par son symbole")
    void recupererMultiplication() {
        IOperation operation = registre.recuperer("*");
        assertInstanceOf(Multiplication.class, operation);
    }

    @Test
    @DisplayName("Récupérer la division par son symbole")
    void recupererDivision() {
        IOperation operation = registre.recuperer("/");
        assertInstanceOf(Division.class, operation);
    }

    @Test
    @DisplayName("Vérifier qu'un symbole est enregistré")
    void contientSymboleConnu() {
        assertTrue(registre.contient("+"));
        assertTrue(registre.contient("-"));
        assertTrue(registre.contient("*"));
        assertTrue(registre.contient("/"));
    }

    @Test
    @DisplayName("Vérifier qu'un symbole inconnu retourne false")
    void neContientPasSymboleInconnu() {
        assertFalse(registre.contient("%"));
    }

    @Test
    @DisplayName("Récupérer un symbole inconnu retourne null")
    void recupererSymboleInconnu() {
        assertNull(registre.recuperer("%"));
    }

    @Test
    @DisplayName("Associer chaque symbole à la bonne opération")
    void mappageParSymbole() {
        assertEquals(5.0, registre.recuperer("+").calculer(3, 2));
        assertEquals(1.0, registre.recuperer("-").calculer(3, 2));
        assertEquals(6.0, registre.recuperer("*").calculer(3, 2));
        assertEquals(1.5, registre.recuperer("/").calculer(3, 2));
    }
}
