package com.mns.cda.calculatrice.operation;

/**
 * Implémentation de la multiplication.
 */
public class Multiplication implements IOperation {

    @Override
    public double calculer(double a, double b) {
        return a * b;
    }
}
