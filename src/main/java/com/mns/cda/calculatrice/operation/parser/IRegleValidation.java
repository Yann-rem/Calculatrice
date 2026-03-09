package com.mns.cda.calculatrice.operation.parser;

/**
 * Interface définissant le contrat d'une règle de validation.
 */
public interface IRegleValidation {

    /**
     * Valide un tableau de jetons.
     *
     * @param jetons jetons à valider
     */
    void valider(String[] jetons);
}
