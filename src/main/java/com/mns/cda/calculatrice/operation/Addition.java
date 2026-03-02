package com.mns.cda.calculatrice.operation;

/**
 * Implémentation de l'addition.
 */
public class Addition implements IOperation {

    @Override
    public String getSymbole() {
        return "+";
    }

    @Override
    public double calculer(double a, double b) {
        return a + b;
    }
}
