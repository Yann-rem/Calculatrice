package com.mns.cda;

import com.mns.cda.calculatrice.config.CalculatriceFactory;
import com.mns.cda.calculatrice.gui.CalculatriceGUI;
import com.mns.cda.calculatrice.operation.*;

import javax.swing.*;

/**
 * Point d'entrée de l'application en mode graphique.
 * Aucune logique métier ici.
 */
public class MainGUI {

    public static void main(String[] args) {
        CalculatriceFactory fabrique = new CalculatriceFactory();

        IHistorique historique = fabrique.creerHistorique();
        CalculatriceService service = fabrique.creerService();

        SwingUtilities.invokeLater(() -> {
            CalculatriceGUI gui = new CalculatriceGUI(service, historique);
            gui.setVisible(true);
        });
    }
}
