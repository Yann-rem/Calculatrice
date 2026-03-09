package com.mns.cda.calculatrice.operation.parser;

import com.mns.cda.calculatrice.operation.OperationRegistry;
import com.mns.cda.calculatrice.operation.exception.OperateurInconnuException;

/**
 * Vérifie que l'opérateur (jeton 1) est connu dans le registre.
 */
public class RegleOperateur implements IRegleValidation {

    private final OperationRegistry registre;

    public RegleOperateur(OperationRegistry registre) {
        this.registre = registre;
    }

    @Override
    public void valider(String[] jetons) {
        if (!registre.contient(jetons[1])) {
            throw new OperateurInconnuException(jetons[1]);
        }
    }
}
