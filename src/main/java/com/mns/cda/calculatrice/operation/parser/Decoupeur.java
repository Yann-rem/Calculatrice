package com.mns.cda.calculatrice.operation.parser;

/**
 * Responsable du découpage d'une expression en jetons.
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

        String[] jetons = expression.trim().split("\\s+");

        if (jetons.length == 1) {
            return decouperSansEspaces(jetons[0]);
        }

        return jetons;
    }

    private String[] decouperSansEspaces(String expression) {
        for (int i = 1; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (!Character.isDigit(c) && c != '.') {
                String valeur1 = expression.substring(0, i);
                String valeur2 = expression.substring(i + 1);

                if (!valeur1.isEmpty() && !valeur2.isEmpty()) {
                    return new String[]{valeur1, String.valueOf(c), valeur2};
                }
            }
        }

        return new String[]{expression};
    }
}
