package com.mns.cda.calculatrice.operation;

import com.mns.cda.calculatrice.operation.model.Calcul;
import com.mns.cda.calculatrice.operation.parser.Decoupeur;
import com.mns.cda.calculatrice.operation.parser.Validateur;

/**
 * Service principal de la calculatrice.
 * Orchestre le découpage, la validation, le calcul et l'historique.
 */
public class CalculatriceService {

    private final Decoupeur decoupeur;
    private final Validateur validateur;
    private final OperationRegistry registre;
    private final IHistorique historique;

    public CalculatriceService(
            Decoupeur decoupeur,
            Validateur validateur,
            OperationRegistry registre,
            IHistorique historique
    ) {
        this.decoupeur = decoupeur;
        this.validateur = validateur;
        this.registre = registre;
        this.historique = historique;
    }

    /**
     * Évalue une expression arithmétique.
     *
     * @param expression expression brute
     * @return résultat du calcul
     */
    public double evaluer(String expression) {
        String[] jetons = decoupeur.decouper(expression);
        validateur.valider(jetons);

        double valeur1 = Double.parseDouble(jetons[0]);
        String operateur = jetons[1];
        double valeur2 = Double.parseDouble(jetons[2]);

        IOperation operation = registre.recuperer(operateur);
        double resultat = operation.calculer(valeur1, valeur2);

        historique.ajouter(new Calcul(expression, resultat));

        return resultat;
    }

    /**
     * Retourne l'historique des calculs.
     *
     * @return historique des calculs
     */
    public IHistorique getHistorique() {
        return historique;
    }
}
