package com.mns.cda.calculatrice.operation;

import java.util.HashMap;
import java.util.Map;

/**
 * Registre des opérations disponibles.
 * Associe chaque symbole d'opérateur à son implémentation.
 */
public class OperationRegistry {

    private final Map<String, IOperation> operations = new HashMap<>();

    /**
     * Enregistre une operation.
     *
     * @param operation opération à enregistrer
     */
    public void enregistrer(IOperation operation) {
        operations.put(operation.getSymbole(), operation);
    }

    /**
     * Récupère l'opération correspondant au symbole donné.
     *
     * @param symbole symbole de l'opérateur
     * @return opération correspondante ou null si inconnue
     */
    public IOperation recuperer(String symbole) {
        return operations.get(symbole);
    }

    /**
     * Vérifie si un symbole est enregistré.
     *
     * @param symbole symbole à vérifier
     * @return true si l'opération existe
     */
    public boolean contient(String symbole) {
        return operations.containsKey(symbole);
    }
}
