package model;

import exception.SoldeInsuffisantException;
import model.Transaction.TypeOperation;

public class CompteEpargne extends Compte {
    private double interestRate;

    public CompteEpargne(String id, double balance, double interestRate) {
        super(id, balance);
        this.interestRate = interestRate;
    }

    public String getType() {
        return "Epargne";
    }

    public void deposit(double amount) {
        setBalance(getBalance() + amount);
        addTransaction(new Transaction(TypeOperation.DEPOT, amount, "Dépôt de " + amount + "€"));
    }

    public void withdraw(double amount) throws SoldeInsuffisantException {
        if (amount > getBalance()) {
            throw new SoldeInsuffisantException();
        }
        setBalance(getBalance() - amount);
        addTransaction(new Transaction(TypeOperation.RETRAIT, amount, "Retrait de " + amount + "€"));
    }

    public void transfer(double amount, TypeOperation type) throws SoldeInsuffisantException {
        if (amount > getBalance()) {
            throw new SoldeInsuffisantException();
        }
        setBalance(getBalance() - amount);
        addTransaction(new Transaction(TypeOperation.VIREMENT, amount, "Virement de " + amount + "€"));
    }

    public void applyInterest(double interest) {
        setBalance(getBalance() + (getBalance() * interestRate) / 100);
        addTransaction(new Transaction(TypeOperation.DEPOT, (getBalance() * interestRate) / 100, "Intérêts appliqués"));
    }
}