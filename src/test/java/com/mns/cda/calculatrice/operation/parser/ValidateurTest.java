package com.mns.cda.calculatrice.operation.parser;

import com.mns.cda.calculatrice.operation.*;
import com.mns.cda.calculatrice.operation.exception.FormatIncorrectException;
import com.mns.cda.calculatrice.operation.exception.OperateurInconnuException;
import com.mns.cda.calculatrice.operation.exception.ValeurNonNumeriqueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidateurTest {

    private Validateur validateur;

    @BeforeEach
    void setUp() {
        OperationRegistry registre = new OperationRegistry();
        registre.enregistrer(new Addition());
        registre.enregistrer(new Soustraction());
        registre.enregistrer(new Multiplication());
        registre.enregistrer(new Division());
        validateur = new Validateur(registre);
    }

    @Test
    @DisplayName("Expression avec nombres positifs est valide")
    void expressionNombresPositifsValide() {
        assertDoesNotThrow(() -> validateur.valider(new String[]{"2", "+", "3"}));
    }

    @Test
    @DisplayName("Expression avec nombres négatifs est valide")
    void expressionNombresNegatifsValide() {
        assertDoesNotThrow(() -> validateur.valider(new String[]{"-3", "+", "-2"}));
    }

    @Test
    @DisplayName("Expression avec nombres décimaux est valide")
    void expressionNombresDecimauxValide() {
        assertDoesNotThrow(() -> validateur.valider(new String[]{"1.5", "*", "2.5"}));
    }

    @Test
    @DisplayName("Trop de jetons lève FormatIncorrectException")
    void tropDeJetons() {
        assertThrows(FormatIncorrectException.class,
                () -> validateur.valider(new String[]{"2", "+", "3", "+", "4"}));
    }

    @Test
    @DisplayName("Pas assez de jetons lève FormatIncorrectException")
    void pasAssezDeJetons() {
        assertThrows(FormatIncorrectException.class,
                () -> validateur.valider(new String[]{"2", "+"}));
    }

    @Test
    @DisplayName("Opérateur inconnu lève OperateurInconnuException")
    void operateurInconnu() {
        assertThrows(OperateurInconnuException.class,
                () -> validateur.valider(new String[]{"2", "%", "3"}));
    }

    @Test
    @DisplayName("Première valeur non numérique lève ValeurNonNumeriqueException")
    void premiereValeurNonNumerique() {
        assertThrows(ValeurNonNumeriqueException.class,
                () -> validateur.valider(new String[]{"abc", "+", "3"}));
    }

    @Test
    @DisplayName("Seconde valeur non numérique lève ValeurNonNumeriqueException")
    void secondeValeurNonNumerique() {
        assertThrows(ValeurNonNumeriqueException.class,
                () -> validateur.valider(new String[]{"2", "+", "xyz"}));
    }
}
