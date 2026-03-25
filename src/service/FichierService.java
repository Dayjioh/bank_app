package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import exception.CompteIntrouvableException;
import model.Client;
import model.Compte;
import model.CompteCourant;
import model.CompteEpargne;

public class FichierService {

    // Sauvegarde une liste de client en CSV
    public void saveToFile(Collection<Client> clients, String filename) throws IOException {
        try (FileWriter fw = new FileWriter(filename)) {
            for (Client client : clients) {
                for (Compte compte : client.getAccounts()) {

                    fw.write(client.getName() + "," + compte.getId() + "," + compte.getType() + ","
                            + compte.getBalance() + "\n");
                }
            }
        }
    }

    // Charge les clients depuis un CSV
    public void loadFromFile(String filename, Banque banque) throws IOException, CompteIntrouvableException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (!banque.clientExists(data[0])) {
                    banque.addClient(new Client(data[0]));
                }
                Client client = banque.getClient(data[0]);
                if (data[2].equals("Courant")) {
                    CompteCourant courant = new CompteCourant(data[1], Double.parseDouble(data[3]));
                    client.addAccount(courant);
                } else {
                    CompteEpargne epargne = new CompteEpargne(data[1], Double.parseDouble(data[3]), 0.0);
                    client.addAccount(epargne);
                }
            }
        }
    }
}