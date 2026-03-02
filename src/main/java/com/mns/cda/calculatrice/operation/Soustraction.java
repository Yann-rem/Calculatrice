package com.mns.cda.calculatrice.operation;

/**
 * Implémentation de la soustraction.
 */
public class Soustraction implements IOperation {

    @Override
    public String getSymbole() {
        return "-";
    }

    @Override
    public double calculer(double a, double b) {
        return a - b;
    }
}
