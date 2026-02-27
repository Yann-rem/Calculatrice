package com.mns.cda.calculatrice.operation;

/**
 * Interface définissant le contrat pour les opérations arithmétiques.
 * Applique le Strategy Pattern.
 */
public interface IOperation {

    /**
     * Effectue le calcul entre deux opérandes.
     *
     * @param a premier opérande
     * @param b second opérande
     * @return résultat du calcul
     */
    double calculer(double a, double b);
}
