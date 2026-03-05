package com.mns.cda;

import com.mns.cda.calculatrice.operation.*;
import com.mns.cda.calculatrice.operation.historique.HistoriqueMySQL;
import com.mns.cda.calculatrice.operation.model.Calcul;
import com.mns.cda.calculatrice.operation.parser.Decoupeur;
import com.mns.cda.calculatrice.operation.parser.Validateur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Point d'entrée de l'application.
 * Gère la boucle de saisie et l'affichage des résultats.
 * Aucune logique métier ici.
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        try (Connection connexion = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/calculatrice", "root", "")) {

            OperationRegistry registre = new OperationRegistry();
            registre.enregistrer(new Addition());
            registre.enregistrer(new Soustraction());
            registre.enregistrer(new Multiplication());
            registre.enregistrer(new Division());
            registre.enregistrer(new Modulo());
            registre.enregistrer(new Puissance());

            Decoupeur decoupeur = new Decoupeur();
            Validateur validateur = new Validateur(registre);
            IHistorique historique = new HistoriqueMySQL(connexion);
            CalculatriceService service = new CalculatriceService(decoupeur, validateur, registre, historique);

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
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
        }
    }
}
