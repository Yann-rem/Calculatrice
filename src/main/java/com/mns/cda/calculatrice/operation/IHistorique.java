package com.mns.cda.calculatrice.operation;

import com.mns.cda.calculatrice.operation.model.Calcul;

import java.util.List;

/**
 * Interface définissant le contrat pour la gestion de l'historique des calculs.
 */
public interface IHistorique {

    /**
     * Ajoute un calcul à l'historique.
     *
     * @param calcul calcul
     */
    void ajouter(Calcul calcul);

    /**
     * Retourne la liste des calculs effectués.
     *
     * @return liste des calculs
     */
    List<Calcul> lister();
}
