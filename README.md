# Calculatrice

Réalisation d'une calculatrice en Java appliquant les principes de la Programmation Orientée Objet, la gestion des
exceptions et les tests unitaires.

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

Modifier le fichier `config.properties` à la racine du projet pour configurer le mode d'historique et la connexion à la
base de données :

```properties
historique.mode=mysql
db.url=jdbc:mysql://localhost:3306/calculatrice
db.user=root
db.password=
```

Pour utiliser l'historique en mémoire, remplacer `mysql` par `memoire`.

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

## Démarche de conception

Après avoir analysé le problème, s'agissant d'un exercice de conception orientée objet, nous avons tout d'abord
identifié les classes, déduit les relations entre celles-ci et établi leur responsabilité unique, puis, avant toute
implémentation, nous avons modélisé notre diagramme de classes UML.

Nous avons commencé par identifier le cœur métier de notre calculatrice, à savoir les opérations. Pour respecter les
principes SOLID, nous avons opté pour le Strategy Pattern en association avec un registre plutôt qu'un contexte
classique : chaque opérateur est associé à son implémentation, ce qui permet d'ajouter une opération sans modifier le
code existant et donc de respecter le principe Open/Closed. De plus, chaque opération déclare elle-même son symbole, ce
qui permet au registre de s'auto-construire à partir des opérations disponibles et d'éviter un mappage en dur.

À ce stade, nous avons brièvement évoqué l'ajout d'opérateurs unaires (racine carrée, factorielle...) en nous disant que
le Strategy Pattern nous protégeait : ajouter un nouveau type d'opération ne casserait rien de l'existant. Mais comme
l'énoncé ne couvre pas ce cas de figure, nous sommes revenus à l'essentiel (KISS oblige).

Nous nous sommes ensuite concentrés sur la conception des classes chargées d'analyser l'expression saisie et de vérifier
sa validité. Nous avons décidé de prendre un peu de liberté par rapport à l'énoncé en choisissant une approche de "
validation par exception" : plutôt que de retourner des codes d'erreur ou des booléens, chaque problème détecté lève une
exception explicite. Après réflexion, nous avons abouti à cette séparation des responsabilités :

- **Decoupeur** : découpe l'expression brute en jetons
- **Validateur** : valide les jetons (format correct, valeurs numériques, opérateur connu) et lève les exceptions
  personnalisées correspondantes
- **CalculatriceService** : orchestre le tout (découpe → valide → récupère l'opération dans le registre → calcule →
  historise)

Cette séparation nous a permis de tester chaque étape indépendamment et de produire des messages d'erreur précis selon
la nature du problème.

Considérant que la matière était suffisante, nous avons ensuite commencé l'implémentation. Nous avons suivi une approche
de développement classique plutôt qu'une approche pilotée par les tests (TDD), mais avec rigueur : chaque classe était
testée immédiatement après son implémentation, ce qui nous a permis de détecter les régressions au fil de l'avancement.

Pour finir, nous nous sommes occupés du point d'entrée de notre application (d'abord en mode console (`Main`), puis en
mode graphique (`MainGUI`)) en veillant à ce qu'aucune logique métier ne s'y trouve : ces classes ne font qu'assembler
les dépendances et déléguer le travail.

## Conception

[Diagramme de classes](docs/diagramme_classes.png)

## Choix de conception

**Strategy Pattern pour les opérations** : Chaque opération arithmétique implémente l'interface `IOperation`. L'ajout d'
un nouvel opérateur se fait en créant une nouvelle classe sans modifier le code existant (Open/Closed Principle).

**Registres pour l'extensibilité** : `OperationRegistry` associe chaque symbole à son implémentation.

**Injection de dépendances par constructeur** : Toutes les dépendances sont injectées via le constructeur (Dependency
Inversion Principle), ce qui facilite les tests et le découplage.

**Séparation des responsabilités** : Le `Decoupeur` découpe l'expression en jetons, le `Validateur` vérifie leur
validité en levant des exceptions personnalisées, le `CalculatriceService` orchestre le tout. Aucune logique métier dans
les classes `Main` et `MainGUI`.

**Initialisation lazy plutôt que Singleton** : La connexion MySQL et l'historique sont créés une seule fois via une
initialisation lazy dans `CalculatriceFactory`. Contrairement au pattern Singleton, cette approche évite le couplage
global et préserve la testabilité, tout en garantissant l'unicité de l'instance.

**Configuration externalisée** : Le choix entre historique en mémoire et MySQL se fait via un fichier de propriétés,
sans modification du code source.

**Exceptions personnalisées** : `FormatIncorrectException`, `OperateurInconnuException`, `ValeurNonNumeriqueException`
et `DivisionParZeroException` permettent une identification claire de chaque type d'erreur.

**Pas de DAO générique** : Le pattern `DAO<T>` n'a pas été retenu car le projet ne comporte qu'une seule entité à
persister (`Calcul`). L'interface `IHistorique` joue le rôle d'un DAO spécialisé, suffisant pour le besoin actuel. Un
DAO générique serait justifié si le projet évoluait vers plusieurs entités.

**Arborescence pragmatique** : Certains packages ne contiennent que peu de fichiers. Nous avons choisi de ne pas créer
de sous-packages supplémentaires lorsque le nombre de classes ne le justifiait pas, privilégiant la lisibilité à une
hiérarchie trop profonde.

**Convention de nommage mixte** : Les noms de classes et méthodes métier sont en français (ex : `Decoupeur`,
`Validateur`, `calculer`, `evaluer`) pour refléter le domaine. Les termes universels en Java restent en anglais (ex :
`get`, `Exception`, `Factory`, `Registry`, `Service`, `parser`, `model`) car les franciser serait contre-productif et
nuirait à la lisibilité pour tout développeur Java.

## Pistes d'amélioration

- Tests de la couche de persistance avec une base en mémoire (H2) ou avec des dépendances simulées via Mockito.
- Gestion des opérateurs unaires (racine carrée, factorielle)
- Support des expressions complexes avec priorité des opérateurs via l'algorithme **Shunting Yard** de Dijkstra (
  voir [Java Program to Implement Shunting Yard Algorithm](https://www.geeksforgeeks.org/java/java-program-to-implement-shunting-yard-algorithm/))
- Validation extensible via **chaîne de responsabilité** : transformer le `Validateur` en pipeline de règles
  indépendantes, chacune responsable d'une seule vérification, permettant l'ajout de nouvelles règles sans modifier le
  code existant (voir [Chaîne de responsabilité](https://refactoring.guru/fr/design-patterns/chain-of-responsibility)).
