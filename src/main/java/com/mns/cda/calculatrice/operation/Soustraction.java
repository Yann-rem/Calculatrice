package com.mns.cda.calculatrice.operation;

/**
 * Implémentation de la soustraction.
 */
public class Soustraction implements IOperation {

    @Override
    public double calculer(double a, double b) {
        return a - b;
    }
}
