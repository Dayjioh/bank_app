package model;

import java.util.ArrayList;

import exception.SoldeInsuffisantException;

public abstract class Compte {

    // attributs
    private String id;
    private double balance;
    private ArrayList<Transaction> transactions;

    // constructeur
    public Compte(String id, double balance) {
        this.id = id;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    // getters
    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    // setters
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // ajoute une transaction
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    // methode concrète
    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    // méthode abstraite
    public abstract void deposit(double amount);

    public abstract void withdraw(double amount) throws SoldeInsuffisantException;

    public abstract String getType();
}