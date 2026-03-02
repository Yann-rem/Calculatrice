package com.mns.cda.calculatrice.operation;

import com.mns.cda.calculatrice.operation.exception.DivisionParZeroException;

/**
 * Implémentation de la division.
 */
public class Division implements IOperation {

    @Override
    public String getSymbole() {
        return "/";
    }

    @Override
    public double calculer(double a, double b) {
        if (b == 0) {
            throw new DivisionParZeroException();
        }
        return a / b;
    }
}
