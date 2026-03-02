package com.mns.cda.calculatrice.operation;

/**
 * Implémentation de la multiplication.
 */
public class Multiplication implements IOperation {

    @Override
    public String getSymbole() {
        return "*";
    }

    @Override
    public double calculer(double a, double b) {
        return a * b;
    }
}
