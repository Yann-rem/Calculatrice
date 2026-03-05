package com.mns.cda.calculatrice.operation;

import com.mns.cda.calculatrice.operation.model.Calcul;

import java.util.List;

/**
 * Interface définissant le contrat pour la gestion de l'historique des calculs.
 * Permet de basculer entre différentes implémentations.
 */
public interface IHistorique {

    /**
     * Ajoute un calcul à l'historique.
     *
     * @param calcul calcul
     */
    void ajouter(Calcul calcul);

    /**
     * Retourne l'historique des calculs.
     *
     * @return historique des calculs
     */
    List<Calcul> lister();

    /**
     * Vide l'historique des calculs.
     */
    void vider();
}
