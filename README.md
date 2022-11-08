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
Rien pour le moment

### Démarrage

Une fois tous les prérequis respectés, pour lancer le projet
```bash
cd  frontend
npm run dev
```
Dans une autre console lancer l'application spring boot au niveau du backend


## Versions
Notre projet sera réalisé en deux versions:

### Version 1.0

Il s'agit de la 1ère version de l'application. Elle peut comporter des bugs et un frontend peu ergonomique. Il se peut aussi que certaines fonctionnalités moins importantes ne soient pas encore testables.
Les fonctionnalités attendus sont :

  - Créer une équipe
  - Gestion du tournoi
  - Lancer les créations de Match
  - Modifier une équipe
  - Renseigner un score

### Version 2.0
Il s'agit de la version finale du logiciel. Elle être stable, facile à utiliser et avec une ergonomie acceptable. Elle comporter des bugs mineurs. Les fonctionnalités attendus sont :

  - Consulter l'historique des tournois   (par l'utilisateur)
  - Consulter les photos                  (par l'utilisateur)
  - Uploader les photos                   (par l'administrateur)
  - Renseigner                            (par l'administrateur)
  - Un system d'alerte par mail           (automatique)

## Auteurs
* **Brou Assamela** _alias_ [@abrou](https://gitlab.istic.univ-rennes1.fr/abrou)
* **Lokonon Bignon M Souvenir** _alias_ [@blokonon](https://gitlab.istic.univ-rennes1.fr/blokonon)
* **Padonou Alexandra Epiphanie** _alias_ [@apadonou](https://gitlab.istic.univ-rennes1.fr/apadonou)
* **Soumare Sekou** _alias_ [@ssoumare](https://gitlab.istic.univ-rennes1.fr/ssoumare)
* **Tra-Lou Toure William** _alias_ [@wtraloutoure](https://gitlab.istic.univ-rennes1.fr/wtraloutoure)


