package com.mns.cda.calculatrice.operation;

import com.mns.cda.calculatrice.operation.exception.DivisionParZeroException;
import com.mns.cda.calculatrice.operation.exception.FormatIncorrectException;
import com.mns.cda.calculatrice.operation.exception.OperateurInconnuException;
import com.mns.cda.calculatrice.operation.exception.ValeurNonNumeriqueException;
import com.mns.cda.calculatrice.operation.historique.HistoriqueEnMemoire;
import com.mns.cda.calculatrice.operation.parser.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatriceServiceIntegrationTest {

    private CalculatriceService service;

    @BeforeEach
    void setUp() {
        OperationRegistry registre = new OperationRegistry();
        registre.enregistrer(new Addition());
        registre.enregistrer(new Soustraction());
        registre.enregistrer(new Multiplication());
        registre.enregistrer(new Division());

        Decoupeur decoupeur = new Decoupeur();
        List<IRegleValidation> regles = List.of(
                new RegleFormat(),
                new RegleValeurNumerique(),
                new RegleOperateur(registre)
        );
        Validateur validateur = new Validateur(regles);
        HistoriqueEnMemoire historique = new HistoriqueEnMemoire();
        service = new CalculatriceService(decoupeur, validateur, registre, historique);
    }

    @Test
    @DisplayName("Addition : 2 + 3 = 5")
    void addition() {
        assertEquals(5.0, service.evaluer("2 + 3"));
    }

    @Test
    @DisplayName("Soustraction : 10 - 4 = 6")
    void soustraction() {
        assertEquals(6.0, service.evaluer("10 - 4"));
    }

    @Test
    @DisplayName("Multiplication : 5 * 7 = 35")
    void multiplication() {
        assertEquals(35.0, service.evaluer("5 * 7"));
    }

    @Test
    @DisplayName("Division : 10 / 2 = 5")
    void division() {
        assertEquals(5.0, service.evaluer("10 / 2"));
    }

    @Test
    @DisplayName("Calcul avec des nombres décimaux")
    void calculNombresDecimaux() {
        assertEquals(3.5, service.evaluer("1.5 + 2.0"));
    }

    @Test
    @DisplayName("Calcul avec un nombre négatif")
    void calculNombreNegatif() {
        assertEquals(-1.0, service.evaluer("-3 + 2"));
    }

    @Test
    @DisplayName("Calcul avec des nombres négatifs")
    void calculNombresNegatifs() {
        assertEquals(-5.0, service.evaluer("-3 + -2"));
    }

    @Test
    @DisplayName("Calcul ajouté à l'historique")
    void calculAjouteAHistorique() {
        service.evaluer("2 + 3");
        assertEquals(1, service.getHistorique().lister().size());
        assertEquals("2 + 3 = 5.0", service.getHistorique().lister().get(0).toString());
    }

    @Test
    @DisplayName("Plusieurs calculs ajoutés à l'historique")
    void plusieursCalculsDansHistorique() {
        service.evaluer("2 + 3");
        service.evaluer("10 / 2");
        assertEquals(2, service.getHistorique().lister().size());
    }

    @Test
    @DisplayName("Calcul en erreur pas ajouté à l'historique")
    void CalculEnErreurPasAjouteAHistorique() {
        assertThrows(DivisionParZeroException.class, () -> service.evaluer("10 / 0"));
        assertTrue(service.getHistorique().lister().isEmpty());
    }

    @Test
    @DisplayName("Division par zéro lève DivisionParZeroException")
    void divisionParZero() {
        assertThrows(DivisionParZeroException.class, () -> service.evaluer("10 / 0"));
    }

    @Test
    @DisplayName("Expression vide lève une exception")
    void expressionVide() {
        assertThrows(IllegalArgumentException.class, () -> service.evaluer(""));
    }

    @Test
    @DisplayName("Format incorrect lève FormatIncorrectException")
    void formatIncorrect() {
        assertThrows(FormatIncorrectException.class, () -> service.evaluer("2 +"));
    }

    @Test
    @DisplayName("Opérateur inconnu lève OperateurInconnuException")
    void operateurInconnu() {
        assertThrows(OperateurInconnuException.class, () -> service.evaluer("2 % 3"));
    }

    @Test
    @DisplayName("Valeur non numérique lève ValeurNonNumeriqueException")
    void valeurNonNumerique() {
        assertThrows(ValeurNonNumeriqueException.class, () -> service.evaluer("abc + 3"));
    }
}
