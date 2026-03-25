# BankApp — Java POO

Application console de gestion bancaire réalisée pour consolider les bases de la POO en Java.

## Fonctionnalités
- Ajouter des clients et des comptes (courant / épargne)
- Déposer et retirer de l'argent
- Afficher les comptes et les soldes
- Sauvegarde et chargement automatique des données en CSV

## Concepts pratiqués
**POO**
- Classe abstraite, héritage, polymorphisme, encapsulation
- Exceptions personnalisées — `SoldeInsuffisantException`, `CompteIntrouvableException`

**Java**
- `HashMap`, `ArrayList`, `Collection`
- `throws` / `throw` — gestion des exceptions
- `FileWriter` / `BufferedReader` — persistance CSV
- Séparation en packages — `model/`, `service/`, `exception/`, `ui/`

## Lancer le projet
```bash
javac -d bin src/**/*.java src/Main.java
java -cp bin Main
```

## Contexte
Deuxième projet Java réalisé pour pratiquer la POO.
