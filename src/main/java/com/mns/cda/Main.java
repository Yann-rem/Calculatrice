package com.mns.cda;

import com.mns.cda.calculatrice.operation.*;
import com.mns.cda.calculatrice.operation.historique.HistoriqueEnMemoire;
import com.mns.cda.calculatrice.operation.model.Calcul;
import com.mns.cda.calculatrice.operation.parser.Decoupeur;
import com.mns.cda.calculatrice.operation.parser.Validateur;

import java.util.Scanner;

/**
 * Point d'entrée de l'application.
 * Gère la boucle de saisie et l'affichage des résultats.
 * Aucune logique métier ici.
 */
public class Main {

    public static void main(String[] args) {

        OperationRegistry registre = new OperationRegistry();
        registre.enregistrer(new Addition());
        registre.enregistrer(new Addition());
        registre.enregistrer(new Multiplication());
        registre.enregistrer(new Division());
        registre.enregistrer(new Modulo());
        registre.enregistrer(new Puissance());

        Decoupeur decoupeur = new Decoupeur();
        Validateur validateur = new Validateur(registre);
        IHistorique historique = new HistoriqueEnMemoire();

        CalculatriceService service = new CalculatriceService(decoupeur, validateur, registre, historique);

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Calculatrice ===");
        System.out.println("Format : valeur1 opérateur valeur2");
        System.out.println("Tapez 'historique' pour voir les l'historique des calculs.");
        System.out.println("Tapez 'quitter' pour sortir.");
        System.out.println();

        while (true) {
            System.out.print("> ");
            String expression = scanner.nextLine();

            if (expression.equalsIgnoreCase("quitter")) {
                System.out.println("Au revoir !");
                break;
            }

            if (expression.equalsIgnoreCase("historique")) {
                if (historique.lister().isEmpty()) {
                    System.out.println("Aucun calcul dans l'historique.");
                    continue;
                }

                for (Calcul calcul : historique.lister()) {
                    System.out.println("  " + calcul);
                }
                continue;
            }

            try {
                double resultat = service.evaluer(expression);
                System.out.println("Résultat : " + resultat);
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }

        scanner.close();
    }
}
