package com.mns.cda.calculatrice.operation.historique;

import com.mns.cda.calculatrice.operation.IHistorique;
import com.mns.cda.calculatrice.operation.model.Calcul;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation de l'historique des calculs avec persistance MySQL.
 */
public class HistoriqueMySQL implements IHistorique {

    private final Connection connexion;

    public HistoriqueMySQL(Connection connexion) {
        this.connexion = connexion;
    }

    @Override
    public void ajouter(Calcul calcul) {

        String sql = """
                INSERT INTO calcul (expression, resultat)
                VALUES (?, ?)
                """;

        try (PreparedStatement stmt =
                     connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, calcul.getExpression());
            stmt.setDouble(2, calcul.getResultat());
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    calcul.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout du calcul.", e);
        }
    }

    @Override
    public List<Calcul> lister() {

        String sql = """
                SELECT id, expression, resultat, horodatage
                FROM calcul
                ORDER BY id ASC
                """;
        List<Calcul> calculs = new ArrayList<>();

        try (PreparedStatement stmt = connexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Calcul calcul = new Calcul(
                        rs.getInt("id"),
                        rs.getString("expression"),
                        rs.getDouble("resultat"),
                        rs.getTimestamp("horodatage").toLocalDateTime()
                );
                calculs.add(calcul);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la lecture de l'historique des calculs.", e);
        }

        return calculs;
    }

    @Override
    public void vider() {
        String sql = "DELETE FROM calcul";

        try (PreparedStatement stmt = connexion.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de l'historique.", e);
        }
    }
}
