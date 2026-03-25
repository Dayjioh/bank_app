package model;

import java.util.ArrayList;

public class Client {

    private String name;
    private ArrayList<Compte> accounts;

    public Client(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Compte> getAccounts() {
        return accounts;
    }

    public void addAccount(Compte c) {
        accounts.add(c);
    }
}