package com.mns.cda.calculatrice.operation.parser;

import com.mns.cda.calculatrice.operation.OperationRegistry;
import com.mns.cda.calculatrice.operation.exception.FormatIncorrectException;
import com.mns.cda.calculatrice.operation.exception.OperateurInconnuException;
import com.mns.cda.calculatrice.operation.exception.ValeurNonNumeriqueException;

/**
 * Responsable de la validation des jetons d'une expression arithmétique.
 * Vérifie le format, les valeurs numériques et l'opérateur.
 */
public class Validateur {

    private final OperationRegistry registre;

    public Validateur(OperationRegistry registre) {
        this.registre = registre;
    }

    /**
     * Valide un tableau de jetons représentant une expression arithmétique.
     *
     * @param jetons jetons à valider
     * @throws FormatIncorrectException    si le nombre de jetons est incorrect
     * @throws ValeurNonNumeriqueException si une valeur n'est pas numérique
     * @throws OperateurInconnuException   si l'opérateur n'est pas reconnu
     */
    public void valider(String[] jetons) {
        validerFormat(jetons);
        validerValeurNumerique(jetons[0]);
        validerOperateur(jetons[1]);
        validerValeurNumerique(jetons[2]);
    }

    private void validerFormat(String[] jetons) {
        if (jetons.length != 3) {
            throw new FormatIncorrectException(
                    "L'expression doit contenir 3 éléments (valeur opérateur valeur)"
            );
        }
    }

    private void validerValeurNumerique(String valeur) {
        try {
            Double.parseDouble(valeur);
        } catch (NumberFormatException e) {
            throw new ValeurNonNumeriqueException(valeur);
        }
    }

    private void validerOperateur(String operateur) {
        if (!registre.contient(operateur)) {
            throw new OperateurInconnuException(operateur);
        }
    }
}
