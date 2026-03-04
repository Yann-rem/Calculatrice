package com.mns.cda.calculatrice.operation.historique;

import com.mns.cda.calculatrice.operation.IHistorique;
import com.mns.cda.calculatrice.operation.model.Calcul;

import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation de l'historique en mémoire.
 */
public class HistoriqueEnMemoire implements IHistorique {

    private final List<Calcul> calculs = new ArrayList<>();

    @Override
    public void ajouter(Calcul calcul) {
        calculs.add(calcul);
    }

    @Override
    public List<Calcul> lister() {
        return List.copyOf(calculs);
    }
}
