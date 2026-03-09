package com.mns.cda.calculatrice.operation.parser;

import com.mns.cda.calculatrice.operation.exception.FormatIncorrectException;

/**
 * Vérifie que l'expression contient exactement 3 jetons.
 */
public class RegleFormat implements IRegleValidation {

    @Override
    public void valider(String[] jetons) {
        if (jetons.length != 3) {
            throw new FormatIncorrectException(
                    "L'expression doit contenir 3 éléments (valeur opérateur valeur)");
        }
    }
}
