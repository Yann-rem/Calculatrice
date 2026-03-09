package com.mns.cda.calculatrice.operation.parser;

import com.mns.cda.calculatrice.operation.exception.ValeurNonNumeriqueException;

/**
 * Vérifie que les valeurs (jetons 0 et 2) sont numériques.
 */
public class RegleValeurNumerique implements IRegleValidation {

    @Override
    public void valider(String[] jetons) {
        validerValeur(jetons[0]);
        validerValeur(jetons[2]);
    }

    private void validerValeur(String valeur) {
        try {
            Double.parseDouble(valeur);
        } catch (NumberFormatException e) {
            throw new ValeurNonNumeriqueException(valeur);
        }
    }
}
