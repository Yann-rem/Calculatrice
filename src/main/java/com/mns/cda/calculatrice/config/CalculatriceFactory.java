package com.mns.cda.calculatrice.config;

import com.mns.cda.calculatrice.operation.*;
import com.mns.cda.calculatrice.operation.historique.HistoriqueEnMemoire;
import com.mns.cda.calculatrice.operation.historique.HistoriqueMySQL;
import com.mns.cda.calculatrice.operation.parser.Decoupeur;
import com.mns.cda.calculatrice.operation.parser.Validateur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Fabrique responsable de l'assemblage des dépendances de l'application.
 */
public class CalculatriceFactory {

    private Connection connection;

    public OperationRegistry creerRegistre() {
        OperationRegistry registre = new OperationRegistry();
        registre.enregistrer(new Addition());
        registre.enregistrer(new Soustraction());
        registre.enregistrer(new Multiplication());
        registre.enregistrer(new Division());
        registre.enregistrer(new Modulo());
        registre.enregistrer(new Puissance());
        return registre;
    }

    public IHistorique creerHistorique() {
        String mode = "mysql";

        if ("mysql".equalsIgnoreCase(mode)) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/calculatrice", "root", "");
                return new HistoriqueMySQL(connection);
            } catch (SQLException e) {
                throw new RuntimeException("Erreur de connexion à la base de données.", e);
            }
        }

        return new HistoriqueEnMemoire();
    }

    public CalculatriceService creerService() {
        OperationRegistry registry = creerRegistre();
        Decoupeur decoupeur = new Decoupeur();
        Validateur validateur = new Validateur(registry);
        IHistorique historique = creerHistorique();

        return new CalculatriceService(decoupeur, validateur, registry, historique);
    }

    public IHistorique getHistorique() {
        return creerService().getHistorique();
    }

    public void fermerConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}
