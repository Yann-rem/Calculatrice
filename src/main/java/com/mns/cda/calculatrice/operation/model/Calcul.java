package com.mns.cda.calculatrice.operation.model;

/**
 * Représente un calcul effectué par la calculatrice.
 */
public class Calcul {

    private final String expression;
    private final double resultat;

    public Calcul(String expression, double resultat) {
        this.expression = expression;
        this.resultat = resultat;
    }

    public String getExpression() {
        return expression;
    }

    public double getResultat() {
        return resultat;
    }

    @Override
    public String toString() {
        return expression + " = " + resultat;
    }
}
