package com.mns.cda.calculatrice.operation.parser;

import java.util.List;

/**
 * Responsable de la validation des jetons d'une expression arithmétique.
 * Délègue la validation à une chaîne de règles implémentant IRegleValidation.
 */
public class Validateur {

    private final List<IRegleValidation> regles;

    public Validateur(List<IRegleValidation> regles) {
        this.regles = regles;
    }

    /**
     * Valide un tableau de jetons en appliquant chaque règle.
     *
     * @param jetons jetons à valider
     */
    public void valider(String[] jetons) {
        for (IRegleValidation regle : regles) {
            regle.valider(jetons);
        }
    }
}
