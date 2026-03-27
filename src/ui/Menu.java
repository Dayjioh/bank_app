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
        try {
            fichierService.loadFromFile("banque.csv", banque);
            System.out.println("Données chargées !");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public void start() {

        int choix = 0;
        while (choix != -1) {
            System.out.println("-------------------------------------------");

            System.out.println("1- Ajouter un client");
            System.out.println("2- Ajouter un compte");
            System.out.println("3- Déposer de l'argent");
            System.out.println("4- Retirer de l'argent");
            System.out.println("5- Effectuer un virement");
            System.out.println("6- Afficher les comptes d'un client");
            System.out.println("7- Quitter");

            System.out.println("-------------------------------------------");

            System.out.print("Entrez un chiffre : ");

            // 
            try {
                choix = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Veuillez saisir un chiffre");
                scanner.nextLine();
            }

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
                        String idClient = "CPT" + System.currentTimeMillis();
                        System.out.println("1- Courant | 2- Epargne ");
                        int accountType = scanner.nextInt();
                        if (accountType == 1) {
                            client.addAccount(new CompteCourant(idClient, 0.0));
                        } else {
                            System.out.println("Taux d'intérêt (%) : ");
                            double interestRate = scanner.nextDouble();
                            client.addAccount(new CompteEpargne(idClient, 0.0, interestRate));
                        }

                    } catch (CompteIntrouvableException e) {
                        System.out.println(e.getMessage());
                    }
                    autoSave();

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
                    autoSave();

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
                    autoSave();

                    break;

                case 5:
                    try {
                        System.out.println("VIREMENT - Nom du client : ");
                        String clientName = scanner.next();
                        Client client = banque.getClient(clientName);
                        for (Compte compte : client.getAccounts()) {
                            System.out.println(compte.getId() + " - " + compte.getType());
                        }
                        System.out.println("VIREMENT - ID client : ");
                        String idClient = scanner.next();
                        System.out.println("VIREMENT - Nom du bénéficiaire : ");
                        String transferName = scanner.next();
                        Client transferClient = banque.getClient(transferName);
                        for (Compte compte : transferClient.getAccounts()) {
                            System.out.println(compte.getId() + " - " + compte.getType());
                        }
                        System.out.println("VIREMENT - ID bénéficiaire : ");
                        String transferIdClient = scanner.next();
                        System.out.println("VIREMENT - Montant : ");
                        double amount = scanner.nextDouble();
                        for (Compte compte : client.getAccounts()) {
                            if (compte.getId().equals(idClient)) {
                                compte.withdraw(amount);
                            }
                        }
                        for (Compte benefCompte : transferClient.getAccounts()) {
                            if (benefCompte.getId().equals(transferIdClient)) {
                                benefCompte.deposit(amount);
                            }
                        }
                        System.out.println("Virement de " + amount + "€ effectué de " + clientName + " vers "
                                + transferName + " avec succès !");
                    } catch (CompteIntrouvableException e) {
                        System.out.println(e.getMessage());
                    } catch (SoldeInsuffisantException e) {
                        System.out.println(e.getMessage());
                    }
                    autoSave();

                    break;
                case 6:
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
                case 7:
                    System.out.println("Bye !");

                    break;
                default:
                    System.out.println("Choix invalide, réessayez.");
                    break;
            }
        }
    }

    // Sauvegarde automatique après chaque opération
    private void autoSave() {
        try {
            fichierService.saveToFile(banque.getAllClients(), "banque.csv");
        } catch (IOException e) {
            System.out.println("Erreur sauvegarde : " + e.getMessage());
        }
    }
}
