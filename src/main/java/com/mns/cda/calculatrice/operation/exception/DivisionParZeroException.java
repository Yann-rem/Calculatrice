package com.mns.cda.calculatrice.operation.exception;

/**
 * Exception pour les divisions par zéro.
 */
public class DivisionParZeroException extends ArithmeticException {

    public DivisionParZeroException() {
        super("Division par zéro impossible.");
    }
}
