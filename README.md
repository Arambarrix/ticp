# TICP

[![forthebadge](http://forthebadge.com/images/badges/built-with-love.svg)](http://forthebadge.com) [![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com) [![forthebadge](https://forthebadge.com/images/badges/made-with-javascript.svg)](https://forthebadge.com)
<!-- Table des matières -->
## Table des matières

- [Table des matières](#table-des-matières)

- [À propos du projet](#à-propos-du-projet)

- [Technologies utilisées](#technologies-utilisées)

- [Structure du projet](./DESIGN.md)

- [Maquette du projet](./DESIGN.md#maquette)

- [Pour commencer](#pour-commencer)

  - [Prérequis](#pré-requis)

  - [Installation](#installation)

  - [Démarrage](#démarrage)

  - [Principes de fonctionnement](#Principes de fonctionnement)

- [Versions](#versions)

- [Auteurs](#auteurs)

- [License](#license)

## À propos du projet

L'objectif du projet est de mettre en applicatioon nos différentes connaissances acquises au travers des cours et afin de travailler sur un projet réel.  

Le TICP est un tournoi de pétanque historique de l’entreprise SCALIAN DS ayant lieu chaque année, entre Avril et Juillet. Ce tournoi permet aux collaborateur de s’inscrire par équipe de 2, 3 ou 4 personnes. Un premier tirage est effectué permettant de créer des poules de 4 équipes.

Une fois les matchs de poules réalisés et les classements mis à jour, un deuxième tirage permet de créer deux tableaux distincts : un tableau or, regroupant les meilleures équipes. Un tableau argent, regroupant le reste des équipes. Dans le cas où le nombre d’équipes participantes n’est pas favorable à l’utilisation de deux tableaux, un 3ème tableau (bronze) peut être créé. [Pus de détails](./cahier_charge_ticp.pdf)

Pour les spécifications techniques, veuillez consulter le [DESIGN.md](DESIGN.md).
L'ensemble des tests de fonctionnalités qui seront éffectués sur le frontend de l'application est détaillé dans le [RECETTE.md](RECETTE.md).



## Technologies utilisées

* [Tailwind CSS](https://tailwindcss.com/) - Framework CSS (front-end)
* [Vue JS](https://vuejs.org/) - Framework JavaScript utilisé pour le frontend
* [Spring Boot](https://spring.io/projects/spring-boot) - Framework Java utilisé ici pour le backend


## Pour commencer

### Pré-requis

Il est requis d'installer:

- Node.js version 16.0 ou plus
- JDK 1.8 ou plus
- Gradle 4+ or Maven 3.2+



### Installation
Consultez le fichier [INSTALL.md](https://gitlab.istic.univ-rennes1.fr/wtraloutoure/ticp-backend/-/blob/main/INSTALL.md)

### Démarrage

Une fois tous les prérequis respectés, pour lancer le projet
```bash
cd  frontend
npm install
npm run dev
```
Dans une autre console lancer l'application spring boot au niveau du backend

### Principes de fonctionnement

- La première action à effectuer est de se connecter en tant que Admin
- Créer un Tournoi (avec les dates de chaque phase)
- Ensuite les utilisateurs pourront inscrire leur équipes
- Une fois la date de fin inscription arrivée, l'insciption des équipes n'est plus possible
- L'admin doit se connecter et générer les Poules (cela se fait automatiquement grâce à un bouton)
- Une fois les Poules générées, rien ne peut être modifié sauf les scores
- Lorsque la phase de poule est terminée, l'administrateur doit se connecter et générer les Tableaux (cela se fait aussi automatiquement)
- A partir de là, les matchs des poules ne sont plus modifiables ni par l'utilisateur, ni par l'administrateur

## Versions
Notre projet sera réalisé en deux versions:

### Version 1.0

Il s'agit de la 1ère version de l'application. Elle peut comporter des bugs et un frontend peu ergonomique. Il se peut aussi que certaines fonctionnalités moins importantes ne soient pas encore testables.
Les fonctionnalités attendus sont :

  - Créer une équipe (terminé)
  - Gestion du tournoi (terminé)
  - Lancer les créations de Match (terminé)
  - Modifier une équipe (à verifier)
  - Renseigner un score (terminé)

### Version 2.0
Il s'agit de la version finale du logiciel. Elle être stable, facile à utiliser et avec une ergonomie acceptable. Elle comporter des bugs mineurs. Les fonctionnalités attendus sont :

  - Consulter l'historique des tournois   (par l'utilisateur)      (terminé)
  - Consulter les photos                  (par l'utilisateur)      (en cours)
  - Uploader les photos                   (par l'administrateur)   (en cours)
  - Renseigner les informations utiles    (par l'administrateur)   (terminé)
  - Un system d'alerte par mail           (automatique)           (à vérifier))

## Auteur
* **Toure William**


