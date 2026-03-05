package com.mns.cda.calculatrice.operation.model;

import java.time.LocalDateTime;

/**
 * Représente un calcul effectué par la calculatrice.
 */
public class Calcul {

    private int id;
    private final String expression;
    private final double resultat;
    private final LocalDateTime horodatage;

    /**
     * Constructeur pour un nouveau calcul (id généré par la base de données).
     */
    public Calcul(String expression, double resultat) {
        this(0, expression, resultat, LocalDateTime.now());
    }

    /**
     * Constructeur complet (utilisé pour la lecture depuis la base de données).
     */
    public Calcul(int id, String expression, double resultat, LocalDateTime horodatage) {
        this.id = id;
        this.expression = expression;
        this.resultat = resultat;
        this.horodatage = horodatage;
    }

    public String getExpression() {
        return expression;
    }

    public double getResultat() {
        return resultat;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getHorodatage() {
        return horodatage;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return expression + " = " + resultat;
    }
}
