package model;

import exception.SoldeInsuffisantException;
import model.Transaction.TypeOperation;

public class CompteCourant extends Compte {
    public CompteCourant(String id, double balance) {
        super(id, balance);

    }

    public String getType() {
        return "Courant";
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
}