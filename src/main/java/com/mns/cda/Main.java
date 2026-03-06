package com.mns.cda;

import com.mns.cda.calculatrice.config.CalculatriceFactory;
import com.mns.cda.calculatrice.operation.*;
import com.mns.cda.calculatrice.operation.model.Calcul;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Point d'entrée de l'application.
 * Gère la boucle de saisie et l'affichage des résultats.
 * Aucune logique métier ici.
 */
public class Main {

    public static void main(String[] args) throws SQLException {

        CalculatriceFactory fabrique = new CalculatriceFactory();

        IHistorique historique = fabrique.creerHistorique();
        CalculatriceService service = fabrique.creerService();

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Calculatrice ===");
        System.out.println("Format : valeur1 opérateur valeur2");
        System.out.println("Tapez 'historique' pour voir les l'historique des calculs.");
        System.out.println("Tapez 'quitter' pour sortir.");
        System.out.println();

        while (true) {
            System.out.print("> ");
            String saisie = scanner.nextLine();

            if (saisie.equalsIgnoreCase("quitter")) {
                System.out.println("Au revoir !");
                break;
            }

            if (saisie.equalsIgnoreCase("historique")) {
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
                double resultat = service.evaluer(saisie);
                System.out.println("Résultat : " + resultat);
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }

        scanner.close();
        fabrique.fermerConnection();
    }
}
