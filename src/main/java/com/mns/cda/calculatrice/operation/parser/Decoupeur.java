package com.mns.cda.calculatrice.operation.parser;

/**
 * Responsable du découpage d'une expresssion en jetons.
 */
public class Decoupeur {

    /**
     * Découpe une expression en jetons : [valeur1, opérateur, valeur2].
     *
     * @param expression expression brute
     * @return tableau de jetons
     */
    public String[] decouper(String expression) {
        if (expression == null || expression.isBlank()) {
            throw new IllegalArgumentException("l'expression ne peut pas être vide");
        }
        return expression.trim().split("\\s+");
    }
}
