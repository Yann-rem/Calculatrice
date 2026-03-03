package com.mns.cda.calculatrice.operation.exception;

/**
 * Exception pour les modulos par zéro.
 */
public class ModuloParZeroException extends RuntimeException {

    public ModuloParZeroException() {
        super("Modulo par zéro impossible.");
    }
}
