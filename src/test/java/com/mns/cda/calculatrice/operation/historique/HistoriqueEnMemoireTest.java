package com.mns.cda.calculatrice.operation.historique;

import com.mns.cda.calculatrice.operation.model.Calcul;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HistoriqueEnMemoireTest {

    private HistoriqueEnMemoire historique;

    @BeforeEach
    void setUp() {
        historique = new HistoriqueEnMemoire();
    }

    @Test
    @DisplayName("L'historique est vide")
    void historiqueVide() {
        assertTrue(historique.lister().isEmpty());
    }

    @Test
    @DisplayName("Ajouter un calcul à l'historique")
    void ajouterUnCalcul() {
        historique.ajouter(new Calcul("2 + 3", 5.0));
        assertEquals(1, historique.lister().size());
    }

    @Test
    @DisplayName("Ajouter plusieurs calculs à l'historique")
    void ajouterPlusieursCalculs() {
        historique.ajouter(new Calcul("2 + 3", 5.0));
        historique.ajouter(new Calcul("10 / 2", 5.0));
        historique.ajouter(new Calcul("10 * 2", 20));
        assertEquals(3, historique.lister().size());
    }

    @Test
    @DisplayName("Vider l'historique")
    void viderHistorique() {
        historique.ajouter(new Calcul("2 + 3", 5.0));
        historique.ajouter(new Calcul("10 / 2", 5.0));
        historique.vider();
        assertTrue(historique.lister().isEmpty());
    }

    @Test
    @DisplayName("Les calculs sont retournés dans l'ordre d'ajout")
    void ordreDesCalculs() {
        historique.ajouter(new Calcul("2 + 3", 5.0));
        historique.ajouter(new Calcul("10 / 2", 5.0));

        List<Calcul> liste = historique.lister();
        assertEquals("2 + 3 = 5.0", liste.get(0).toString());
        assertEquals("10 / 2 = 5.0", liste.get(1).toString());
    }

    @Test
    @DisplayName("La liste retournée est non modifiable")
    void listeNonModifiable() {
        historique.ajouter(new Calcul("2 + 3", 5.0));
        List<Calcul> liste = historique.lister();
        assertThrows(UnsupportedOperationException.class, () -> liste.add(new Calcul("1 + 1", 2.0)));
    }
}
