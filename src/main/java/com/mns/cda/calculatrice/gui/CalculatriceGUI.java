package com.mns.cda.calculatrice.gui;

import com.mns.cda.calculatrice.operation.CalculatriceService;
import com.mns.cda.calculatrice.operation.IHistorique;
import com.mns.cda.calculatrice.operation.model.Calcul;

import javax.swing.*;
import java.awt.*;

/**
 * Interface graphique Swing de la calculatrice.
 * Aucune logique métier ici, tout est délégué à CalculatriceService.
 */
public class CalculatriceGUI extends JFrame {

    private final CalculatriceService service;
    private final IHistorique historique;

    private final JTextField champExpression;
    private final JLabel labelResultat;
    private final JTextArea zoneHistorique;

    public CalculatriceGUI(CalculatriceService service, IHistorique historique) {
        this.service = service;
        this.historique = historique;

        setTitle("Calculatrice");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panneau du haut : saisie et calcul
        JPanel panneauSaisie = new JPanel(new BorderLayout(5, 5));
        panneauSaisie.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        champExpression = new JTextField();
        champExpression.setFont(new Font("SansSerif", Font.PLAIN, 18));
        champExpression.setToolTipText("Ex: 2 + 3");

        JButton boutonCalculer = new JButton("Calculer");
        boutonCalculer.setFont(new Font("SansSerif", Font.BOLD, 14));

        panneauSaisie.add(new JLabel("Expression :"), BorderLayout.NORTH);
        panneauSaisie.add(champExpression, BorderLayout.CENTER);
        panneauSaisie.add(boutonCalculer, BorderLayout.EAST);

        // Panneau central : résultat
        JPanel panneauResultat = new JPanel(new BorderLayout());
        panneauResultat.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        labelResultat = new JLabel("Résultat : ");
        labelResultat.setFont(new Font("SansSerif", Font.BOLD, 20));

        panneauResultat.add(labelResultat, BorderLayout.CENTER);

        // Panneau du bas : historique
        JPanel panneauHistorique = new JPanel(new BorderLayout(5, 5));
        panneauHistorique.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        zoneHistorique = new JTextArea(8, 30);
        zoneHistorique.setEditable(false);
        zoneHistorique.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(zoneHistorique);

        JPanel panneauBoutons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton boutonHistorique = new JButton("Historique");
        JButton boutonVider = new JButton("Vider");
        panneauBoutons.add(boutonHistorique);
        panneauBoutons.add(boutonVider);

        panneauHistorique.add(panneauBoutons, BorderLayout.NORTH);
        panneauHistorique.add(scrollPane, BorderLayout.CENTER);

        add(panneauSaisie, BorderLayout.NORTH);
        add(panneauResultat, BorderLayout.CENTER);
        add(panneauHistorique, BorderLayout.SOUTH);

        boutonCalculer.addActionListener(e -> calculer());
        champExpression.addActionListener(e -> calculer());
        boutonHistorique.addActionListener(e -> afficherHistorique());
        boutonVider.addActionListener(e -> viderHistorique());
    }

    private void calculer() {
        String expression = champExpression.getText().trim();

        if (expression.isEmpty()) {
            labelResultat.setText("Résultat : veuillez saisir une expression.");
            return;
        }

        try {
            double resultat = service.evaluer(expression);
            labelResultat.setText("Résultat : " + resultat);
            champExpression.selectAll();
        } catch (Exception e) {
            labelResultat.setText("Erreur : " + e.getMessage());
        }
    }

    private void afficherHistorique() {
        var calculs = historique.lister();

        if (calculs.isEmpty()) {
            zoneHistorique.setText("Aucun calcul dans l'historique.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Calcul calcul : calculs) {
            sb.append(calcul).append("\n");
        }
        zoneHistorique.setText(sb.toString());
    }

    private void viderHistorique() {
        historique.vider();
        zoneHistorique.setText("Historique vidé.");
    }
}
