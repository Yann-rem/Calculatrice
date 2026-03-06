package com.mns.cda.calculatrice.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Charge la configuration de l'application depuis le fichier config.properties.
 */
public class Configuration {

    private final Properties properties;

    public Configuration() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Impossible de charger le fichier config.properties", e);
        }
    }

    public String getHistoriqueMode() {
        return properties.getProperty("historique.mode", "memoire");
    }

    public String getDbUrl() {
        return properties.getProperty("db.url");
    }

    public String getDbUser() {
        return properties.getProperty("db.user");
    }

    public String getDbPassword() {
        return properties.getProperty("db.password", "");
    }
}
