package com.mns.cda.calculatrice.operation;

/**
 * Interface définissant le contrat pour les opérations arithmétiques.
 * Applique le Strategy Pattern.
 */
public interface IOperation {

    /**
     * Retourne le symbole de l'opérateur.
     *
     * @return symbole de l'opérateur (ex : "+", "-", "*", "/")
     */
    String getSymbole();

    /**
     * Effectue le calcul entre deux opérandes.
     *
     * @param a premier opérande
     * @param b second opérande
     * @return résultat du calcul
     */
    double calculer(double a, double b);
}
