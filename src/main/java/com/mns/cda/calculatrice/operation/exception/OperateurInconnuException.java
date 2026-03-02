package com.mns.cda.calculatrice.operation.exception;

/**
 * Exception pour les opérateurs inconnus.
 */
public class OperateurInconnuException extends RuntimeException {

    public OperateurInconnuException(String operateur) {
        super("Opérateur inconnu : " + operateur + ".");
    }
}
