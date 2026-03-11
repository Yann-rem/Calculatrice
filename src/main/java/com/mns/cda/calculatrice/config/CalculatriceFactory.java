package com.mns.cda.calculatrice.config;

import com.mns.cda.calculatrice.operation.*;
import com.mns.cda.calculatrice.operation.historique.HistoriqueEnMemoire;
import com.mns.cda.calculatrice.operation.historique.HistoriqueMySQL;
import com.mns.cda.calculatrice.operation.parser.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Fabrique responsable de l'assemblage des dépendances de l'application.
 */
public class CalculatriceFactory {

    private final IHistorique historique;
    private final CalculatriceService service;
    private Connection connection;

    public CalculatriceFactory() {
        Configuration configuration = new Configuration();

        this.historique = creerHistorique(configuration);

        OperationRegistry registre = creerRegistre();
        Decoupeur decoupeur = new Decoupeur();
        List<IRegleValidation> regles = List.of(
                new RegleFormat(),
                new RegleValeurNumerique(),
                new RegleOperateur(registre)
        );
        Validateur validateur = new Validateur(regles);

        this.service = new CalculatriceService(decoupeur, validateur, registre, historique);
    }

    public IHistorique getHistorique() {
        return historique;
    }

    public CalculatriceService getService() {
        return service;
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

    private OperationRegistry creerRegistre() {
        OperationRegistry registre = new OperationRegistry();
        registre.enregistrer(new Addition());
        registre.enregistrer(new Soustraction());
        registre.enregistrer(new Multiplication());
        registre.enregistrer(new Division());
        registre.enregistrer(new Modulo());
        registre.enregistrer(new Puissance());
        return registre;
    }

    private IHistorique creerHistorique(Configuration configuration) {
        String mode = configuration.getHistoriqueMode();

        if ("mysql".equalsIgnoreCase(mode)) {
            try {
                connection = DriverManager.getConnection(
                        configuration.getDbUrl(),
                        configuration.getDbUser(),
                        configuration.getDbPassword());
                return new HistoriqueMySQL(connection);
            } catch (SQLException e) {
                throw new RuntimeException("Erreur de connexion à la base de données.", e);
            }
        }

        return new HistoriqueEnMemoire();
    }
}
