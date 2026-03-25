package ui;

import java.io.IOException;
import java.util.Scanner;

import exception.CompteIntrouvableException;
import exception.SoldeInsuffisantException;
import model.Client;
import model.Compte;
import model.CompteCourant;
import model.CompteEpargne;
import service.Banque;
import service.FichierService;

public class Menu {
    private Scanner scanner;
    private Banque banque;
    private FichierService fichierService;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.banque = new Banque();
        this.fichierService = new FichierService();
    }

    public void start() {

        int choix = 0;
        while (choix != 7) {

            System.out.println("1- Ajouter un client");
            System.out.println("2- Ajouter un compte");
            System.out.println("3- Déposer de l'argent");
            System.out.println("4- Retirer de l'argent");
            System.out.println("5- Afficher les comptes d'un client");
            System.out.println("6- Sauvegarder");
            System.out.println("7- Quitter");

            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    System.out.println("Entrez le nom du client : ");
                    String nom = scanner.next();
                    // crée le client et ajoute le à la banque
                    banque.addClient(new Client(nom));
                    break;
                case 2:
                    try {
                        System.out.println("Nom du client : ");
                        String clientName = scanner.next();
                        Client client = banque.getClient(clientName);
                        System.out.println("ID client : ");
                        String idClient = scanner.next();
                        System.out.println("1- Courant | 2- Epargne ");
                        int accountType = scanner.nextInt();
                        if (accountType == 1) {
                            client.addAccount(new CompteCourant(idClient, 0.0));
                        } else {
                            client.addAccount(new CompteEpargne(idClient, 0.0, 0.0));
                        }

                    } catch (CompteIntrouvableException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("DEPOT - Nom du client : ");
                        String clientName = scanner.next();
                        Client client = banque.getClient(clientName);
                        for (Compte compte : client.getAccounts()) {
                            System.out.println(compte.getId() + " - " + compte.getType());
                        }
                        System.out.println("DEPOT - ID client : ");
                        String idClient = scanner.next();
                        System.out.println("DEPOT - Montant : ");
                        double amount = scanner.nextDouble();
                        for (Compte compte : client.getAccounts()) {
                            if (compte.getId().equals(idClient)) {
                                compte.deposit(amount);
                            }
                        }
                    } catch (CompteIntrouvableException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("RETRAIT - Nom du client : ");
                        String clientName = scanner.next();
                        Client client = banque.getClient(clientName);
                        for (Compte compte : client.getAccounts()) {
                            System.out.println(compte.getId() + " - " + compte.getType());
                        }
                        System.out.println("RETRAIT - ID client : ");
                        String idClient = scanner.next();
                        System.out.println("RETRAIT - Montant : ");
                        double amount = scanner.nextDouble();
                        for (Compte compte : client.getAccounts()) {
                            if (compte.getId().equals(idClient)) {
                                compte.withdraw(amount);
                            }
                        }
                    } catch (CompteIntrouvableException e) {
                        System.out.println(e.getMessage());
                    } catch (SoldeInsuffisantException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    try {

                        System.out.println("Accounts - Nom du client : ");
                        String clientName = scanner.next();
                        Client client = banque.getClient(clientName);
                        for (Compte compte : client.getAccounts()) {
                            System.out
                                    .println(compte.getId() + " - " + compte.getType() + " - " + compte.getBalance()
                                            + "€");
                        }
                    } catch (CompteIntrouvableException e) {
                        System.out.println(e.getMessage());

                    }
                    break;
                case 6:
                    try {
                        fichierService.saveToFile(banque.getAllClients(), "banque.csv");
                        System.out.println("Sauvegarde réussie !");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                        System.out.println("Bye !");

                    break;
                default:
                    System.out.println("Choix invalide, réessayez.");
            }
        }
    }
}
