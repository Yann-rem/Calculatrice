package com.mns.cda.calculatrice.operation;

/**
 * Implémentation de la puissance.
 */
public class Puissance implements IOperation {

    @Override
    public String getSymbole() {
        return "^";
    }

    @Override
    public double calculer(double a, double b) {
        return Math.pow(a, b);
    }
}
