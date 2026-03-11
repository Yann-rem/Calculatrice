# Calculatrice

Implémentation d’une calculatrice en Java illustrant les principes de la programmation orientée objet (POO), la gestion des exceptions et les tests unitaires et d'intégrations.

## Auteurs

| Nom                | Contribution                                 |
|--------------------|----------------------------------------------|
| Arnaud BENACQUISTA | Participation égale sur l'ensemble du projet |
| Clément TANCHOT    | Participation égale sur l'ensemble du projet |
| Yannick REMY       | Participation égale sur l'ensemble du projet |

## Prérequis

- JDK 21
- Maven
- MySQL 8

## Lancement

### Configuration

Copier le fichier d'exemple puis le modifier :

```bash
cp config.properties.example config.properties
```

Par défaut, l'historique fonctionne en mémoire. Pour utiliser MySQL, remplacer `memoire` par `mysql` et renseigner les paramètres de connexion :

```properties
historique.mode=memoire
db.url=jdbc:mysql://localhost:3306/calculatrice
db.user=root
db.password=
```

### Base de données

Si le mode MySQL est choisi, exécuter le script d'initialisation :

```bash
mysql -u root -p < src/main/resources/sql/init.sql
```

### Mode console

Lancer la classe `Main`.

### Mode graphique

Lancer la classe `MainGUI`.

## Tests

Si Maven est installé globalement :

```bash
mvn test
```

Sinon, utiliser le terminal intégré d'IntelliJ ou lancer les tests via clic droit sur le dossier `test` > Run.

## Conception

Le diagramme de classes UML du projet :

![Diagramme de classes](docs/diagramme_classes.png)

[Voir l'image en taille réelle](docs/diagramme_classes.png)

## Choix de conception

**Strategy Pattern pour les opérations** : Chaque opération arithmétique implémente l'interface `IOperation`. L'ajout d'un nouvel opérateur se fait en créant une nouvelle classe sans modifier le code existant (Open/Closed Principle).

**Registres pour l'extensibilité** : `OperationRegistry` associe chaque symbole à son implémentation.

**Injection de dépendances par constructeur** : Toutes les dépendances sont injectées via le constructeur (Dependency Inversion Principle), ce qui facilite les tests et le découplage.

**Séparation des responsabilités** : Le `Decoupeur` découpe l'expression en jetons, le `Validateur` vérifie leur validité en levant des exceptions personnalisées, le `CalculatriceService` orchestre le tout. Aucune logique métier dans les classes `Main` et `MainGUI`.

**Factory avec assemblage au démarrage** : `CalculatriceFactory` assemble toutes les dépendances dans son constructeur et expose de simples getters. Pas de lazy initialization ni de Singleton : tout est créé une seule fois au démarrage, ce qui rend le flux explicite et prévisible.

**Configuration externalisée** : Le choix entre historique en mémoire et MySQL se fait via un fichier de propriétés, sans modification du code source.

**Exceptions personnalisées** : `FormatIncorrectException`, `OperateurInconnuException`, `ValeurNonNumeriqueException` et `DivisionParZeroException` permettent une identification claire de chaque type d'erreur.

**Pas de DAO générique** : Le pattern `DAO<T>` n'a pas été retenu car le projet ne comporte qu'une seule entité à persister (`Calcul`). L'interface `IHistorique` joue le rôle d'un DAO spécialisé, suffisant pour le besoin actuel. Un DAO générique serait justifié si le projet évoluait vers plusieurs entités.

**Arborescence pragmatique** : Certains packages ne contiennent que peu de fichiers. Aucun sous-package supplémentaire n’a été créé lorsque le nombre de classes ne le justifiait pas, afin de privilégier la lisibilité à une hiérarchie trop profonde.

**Chaîne de responsabilité pour la validation** : Le `Validateur` délègue la validation à une liste de règles implémentant `IRegleValidation` (`RegleFormat`, `RegleValeurNumerique`, `RegleOperateur`). Ajouter une nouvelle règle de validation se fait en créant une classe, sans modifier le `Validateur` (Open/Closed Principle).

**Convention de nommage mixte** : Les noms de classes et méthodes métier sont en français (ex : `Decoupeur`, `Validateur`, `calculer`, `evaluer`) pour refléter le domaine. Les termes universels en Java restent en anglais (ex : `get`, `Exception`, `Factory`, `Registry`, `Service`, `parser`, `model`) car les franciser serait contre-productif et nuirait à la lisibilité pour tout développeur Java.

## Pistes d'amélioration

- Tests de la couche de persistance avec une base en mémoire (H2) ou avec des dépendances simulées via Mockito.
- Gestion des opérateurs unaires (racine carrée, factorielle)
- Support des expressions complexes avec priorité des opérateurs via l'algorithme **Shunting Yard** de Dijkstra (voir [Java Program to Implement Shunting Yard Algorithm](https://www.geeksforgeeks.org/java/java-program-to-implement-shunting-yard-algorithm/))
