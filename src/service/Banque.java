package service;

import java.util.Collection;
import java.util.HashMap;

import exception.CompteIntrouvableException;
import model.Client;

public class Banque {

    // HashMap : clé = nom du client, valeur = objet Client
    // Permet de retrouver un client instantanément par son nom
    private HashMap<String, Client> clients;

    // Initialise la map vide à la création de la banque
    public Banque() {
        this.clients = new HashMap<>();
    }

    // Ajoute un client — son nom sert de clé unique
    public void addClient(Client c) {
        clients.put(c.getName(), c);
    }

    // Récupère un client par son nom — retourne null si introuvable
    public Client getClient(String name) throws CompteIntrouvableException{
        if (clients.get(name)==null) {
            throw new CompteIntrouvableException();
        }
        return clients.get(name);
    }

    // Retourne tous les clients — values() extrait uniquement les valeurs de la map
    public Collection<Client> getAllClients() {
        return clients.values();
    }
}