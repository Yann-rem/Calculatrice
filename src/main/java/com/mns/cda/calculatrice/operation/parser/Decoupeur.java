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

        String nettoye = expression.trim();

        // Recherche d'un caractère non numérique, non point ou non espace.
        for (int i = 1; i < nettoye.length(); i++) {
            char c = nettoye.charAt(i);
            if (!Character.isDigit(c) && c != '.' && c != ' ') {
                String valeur1 = nettoye.substring(0, i).trim();
                String valeur2 = nettoye.substring(i + 1).trim();

                if (!valeur1.isEmpty() && !valeur2.isEmpty()) {
                    return new String[]{valeur1, String.valueOf(c), valeur2};
                }
            }
        }

        // Le Validateur gère l'exception en dernier recours.
        return new String[]{nettoye};
    }
}
