// src/model/Transaction.java
package model;

public class Transaction {

    public enum TypeOperation {
        DEPOT, RETRAIT, VIREMENT
    }

    private TypeOperation type;
    private double montant;
    private String description;

    public Transaction(TypeOperation type, double montant, String description) {
        this.type = type;
        this.montant = montant;
        this.description = description;
    }

    public TypeOperation getType() {
        return type;
    }

    public double getMontant() {
        return montant;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + type + "] " + montant + "€ — " + description;
    }
}