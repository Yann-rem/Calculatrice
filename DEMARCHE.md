# Démarche de conception

## Analyse et modélisation

Après analyse du problème fonctionnel, nous avons identifié les principales classes du domaine, défini leurs responsabilités et établi les relations entre elles. Avant toute implémentation, nous avons modélisé un diagramme de classes UML afin de disposer d’une vision claire de l’architecture cible.

## Conception du cœur métier

Nous avons commencé par le cœur métier de la calculatrice : les opérations arithmétiques. Pour respecter les principes SOLID, nous avons opté pour le Strategy Pattern associé à un registre plutôt qu'un contexte classique. Chaque opérateur est associé à son implémentation via une `Map<String, IOperation>`, ce qui permet d'ajouter une opération sans modifier le code existant (Open/Closed Principle). De plus, chaque opération déclare elle-même son symbole, ce qui permet au registre de s'auto-construire à partir des opérations disponibles et d'éviter tout mappage en dur.

Nous avons brièvement envisagé la gestion d'opérateurs unaires (racine carrée, factorielle), en constatant que le Strategy Pattern nous protégeait déjà : ajouter un nouveau type d'opération ne casserait rien de l'existant. Cependant, l'énoncé se limitant au format `Valeur1 Opérateur Valeur2`, nous avons choisi de ne pas anticiper ce besoin (principe YAGNI).

## Conception de l'analyse et de la validation

Nous nous sommes ensuite concentrés sur les classes chargées d'analyser l'expression saisie et de vérifier sa validité. En prenant un peu de liberté par rapport à l'énoncé, nous avons séparé ces deux responsabilités (SRP) :

- Le **Decoupeur** découpe l'expression brute en jetons via un algorithme qui identifie l'opérateur parmi les caractères non numériques, gérant ainsi les expressions avec ou sans espaces.
- Le **Validateur** vérifie les jetons selon un pipeline de "validation par exception" : format correct (3 jetons attendus), valeurs numériques et opérateur connu. Chaque problème détecté lève une exception personnalisée explicite plutôt qu'un simple booléen.

Cette séparation garantit que chaque classe a une responsabilité unique et que les exceptions personnalisées (`FormatExpressionException`, `ValeurNonNumeriqueException`, `OperateurInconnuException`) permettent une identification claire de chaque type d'erreur.

## Orchestration

Nous avons ensuite créé un service pour orchestrer l'ensemble du flux :

1. Le `Decoupeur` découpe l'expression en jetons
2. Le `Validateur` vérifie la validité des jetons
3. L'`OperationRegistry` fournit l'opération correspondante
4. L'opération effectue le calcul
5. Le résultat est ajouté à l'historique

Le `CalculatriceService` orchestre cette chaîne sans contenir de logique métier propre, chaque étape étant déléguée au composant approprié.

## Point d'entrée

Pour le point d'entrée de l'application, nous avons veillé à ce que le `Scanner` reste confiné dans la classe `Main` et ne soit jamais injecté dans les classes métier. L'entrée utilisateur est récupérée sous forme de `String` puis transmise au service, ce qui rend toutes les entités testables indépendamment de la source de saisie.

## Mise en place du projet

Le projet a été initialisé avec Maven et JDK 21. Nous avons immédiatement ajouté la dépendance JUnit 5 afin d'être prêts pour le testing continu dès la première ligne de code. La structure des packages dans `test` reflète celle de `main`, conformément à la convention Maven, ce qui permet aux tests d'accéder aux classes avec une visibilité `package-private`.

## Implémentation et tests

Nous avons suivi une approche de développement classique plutôt qu'une approche pilotée par les tests (TDD), mais avec rigueur : chaque classe était testée immédiatement après son implémentation. Nos commits suivent le principe du commit atomique — chaque commit livre un changement cohérent et fonctionnel, typiquement une classe et ses tests ensemble (ex : `feat: ajout d'Addition avec tests unitaires`).

L'implémentation a suivi le même ordre que la modélisation :

1. Le cœur métier : les classes implémentant `IOperation` avec leurs tests unitaires
2. La gestion de l'expression : `Decoupeur` et `Validateur` avec leurs tests unitaires
3. L'orchestrateur : `CalculatriceService` — dont les tests constituent des tests d'intégration plutôt que des tests unitaires, car ils vérifient le bon fonctionnement de la chaîne complète (découpage → validation → calcul)
4. Le point d'entrée : `Main`, sans aucune logique métier

## Extensions

Nous avons ensuite enrichi l'application avec les points optionnels de l'énoncé :

**Ajout de nouvelles opérations** — `Modulo` et `Puissance` ont été ajoutés sans modifier aucune classe existante, en créant simplement de nouvelles implémentations d'`IOperation` et en les enregistrant dans le registre. Cela démontre concrètement le respect de l'Open/Closed Principle.

**Historique des calculs** — L'interface `IHistorique` définit le contrat (ajouter, lister, vider) et permet de basculer entre différentes implémentations sans modifier le reste du code (Dependency Inversion Principle) :
- `HistoriqueEnMemoire` : stockage en mémoire via une `ArrayList`, avec `List.copyOf()` pour protéger l'encapsulation
- `HistoriqueMySQL` : persistance en base de données avec JDBC et `PreparedStatement`

Le choix de l'implémentation est externalisé dans un fichier `application.properties` et géré par un `HistoriqueRegistry` qui utilise des `Supplier` pour l'instanciation différée, évitant ainsi toute connexion inutile à la base de données.

**Interface graphique** — Une interface Swing (`CalculatriceGUI`) a été ajoutée avec un second point d'entrée (`MainGUI`), démontrant que la couche métier est totalement indépendante de l'interface utilisateur.
