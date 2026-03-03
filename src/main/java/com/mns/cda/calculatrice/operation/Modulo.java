package com.mns.cda.calculatrice.operation;

import com.mns.cda.calculatrice.operation.exception.ModuloParZeroException;

/**
 * Implémentation du modulo.
 */
public class Modulo implements IOperation {

    @Override
    public String getSymbole() {
        return "%";
    }

    @Override
    public double calculer(double a, double b) {
        if (b == 0) {
            throw new ModuloParZeroException();
        }
        return a % b;
    }
}
