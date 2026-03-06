package com.mns.cda.calculatrice.operation.exception;

/**
 * Exception pour les valeurs non numériques.
 */
public class ValeurNonNumeriqueException extends RuntimeException {

    public ValeurNonNumeriqueException(String valeur) {
        super("Valeur non numérique : " + valeur + ".");
    }
}
