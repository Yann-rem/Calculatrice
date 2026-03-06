# Sujet — Calculatrice Java

## Objectif du projet

Concevoir et développer une calculatrice simple en Java en appliquant rigoureusement les principes de la Programmation Orientée Objet, la gestion des exceptions et les tests unitaires.

Le projet se réalise en groupe de 3 personnes.

## Étape 0 — Conception (obligatoire)

Avant toute ligne de code :

- Analyser le problème fonctionnel.
- Identifier les responsabilités principales de l'application.
- Proposer un diagramme de classes UML comprenant au minimum : les classes métier, leurs responsabilités et leurs relations (association, dépendance).

Le diagramme devra refléter une séparation claire des rôles.

## Étape 1 — Saisie de l'expression

Le programme doit permettre à l'utilisateur de saisir une expression mathématique simple sous la forme :

```
Valeur1 Opérateur Valeur2
```

Exemples : `2 + 3`, `10 / 2`, `5 * 7`

Contraintes :

- Les valeurs peuvent être entières ou décimales.
- Les opérateurs autorisés sont : `+`, `-`, `*`, `/`.

## Étape 2 — Analyse et validation de l'expression

Implémenter un composant chargé d'analyser l'expression saisie et de vérifier sa validité syntaxique et sémantique.

La validation ne doit pas retourner simplement `TRUE` / `FALSE`.

Gestion des erreurs attendue via des exceptions personnalisées :

- Format incorrect
- Opérateur inconnu
- Division par zéro
- Valeur non numérique

Chaque erreur doit être identifiée clairement.

## Étape 3 — Calcul du résultat

Si l'expression est valide, le calcul est effectué et le résultat est affiché à l'utilisateur.

En cas d'erreur, un message clair et compréhensible est affiché. L'application ne doit jamais planter brutalement.

## Étape 4 — Bonnes pratiques POO (exigées)

Le projet devra démontrer :

- Respect du principe de responsabilité unique (SRP)
- Encapsulation des données
- Usage pertinent des interfaces ou classes abstraites si nécessaire
- Absence de logique métier dans la classe Main
- Code lisible, structuré et commenté

## Étape 5 — Tests unitaires (obligatoires)

Écrire des tests unitaires avec JUnit couvrant au minimum :

- Les calculs corrects
- Les cas d'erreur
- Les opérateurs autorisés
- Les cas limites (division par zéro, valeurs invalides)

Les tests doivent être indépendants, reproductibles et compréhensibles.

## Étape 6 — Extensions (optionnelles)

Selon l'avancement :

- Ajout de nouveaux opérateurs
- Historique des calculs (en mémoire)
- Amélioration de l'interface utilisateur

L'usage d'une base de données est facultatif et non prioritaire.
