# PRESENTATION DU DOCUMENT
Ce document représente l'ensemble des tests des fonctionnalités du logiciel de tournoi de pétanque et leur état.


# FONCTIONNALITES DU LOGICIEL
- Gestion du tournoi
- Gestion des équipes du tournoi


# DESCRIPTION DES FONCTIONNALITES
## Gestion du tournoi
- Création de tournoi (terminé)
- Génération de la phase de poules (terminé)
- Génération des tableaux finaux du tournoi (terminé)
- Gestion des matchs (terminé)


## Gestion des équipes
- Création des équipes (terminé)
- Modification des équipes (implémenté en backend)
- Suppression des équipes (implémenté en backend)
- Création des joueurs (terminé)
- Modification du nom et du prénom du joueur (implémenté en backend)
- Modification de l'équipe du joueur (implémenté en backend)
- Suppression des joueurs (implémenté en backend)


# DESCRIPTION DES TESTS DES FONCTIONNALITES
## Gestion des équipes
### Création des équipes (Possible uniquement avant la fin des inscriptions)

| N°  | Action  | Statut  | Mise à jour  |
|---|---|---|---|
| 1  | Je peux créer une équipe  | Oui  | 11/01/2023  |
| 2  | Je renseigne le nom de l'equipe  | Oui  | 11/01/2023  |


### Modification des équipes (Possible uniquement avant la fin des inscriptions)
| N°  | Action  | Statut  | Mise à jour  |
|---|---|---|---|
| 1  | Je peux modifier une équipe  | Oui en backend  | 11/01/2023  |
| 2  | Je renseigne le nom de l'équipe | Oui en backend  | 11/01/2023  |


### Suppression des équipes (Possible uniquement avant la fin des inscriptions)
| N°  | Action  | Statut  | Mise à jour  |
|---|---|---|---|
| 1  | Je peux suprrimer une équipe  | oui en backend  | 11/01/2023  |
| 2  | Je clique sur le bouton "supprimer équipe" |  Non | 11/01/2023  |


### Création des joueurs (Possible uniquement avant la fin des inscriptions)
| N°  | Action  | Statut  | Mise à jour  |
|---|---|---|---|
| 1  | Je peux renseigner des joueurs de l'équipe | Oui  | 11/01/2023  |
| 2  | Je sélectionne le nombre de joueurs à inscrire  | exclu | 11/01/2023  |
| 3  | Je renseigne les noms et prénoms des joueurs  | Oui  | 11/01/2023  |

### Modification du nom et du prénom d'un joueur (Possible uniquement avant la fin des inscriptions)
| N°  | Action  | Statut  | Mise à jour  |
|---|---|---|---|
| 1  | Je peux modifier un joueur  | Oui en backend  | 11/01/2023  |
| 2  | Je renseigne le nom et le prénom du joueur | Oui  | 11/01/2023  |

### Modification de l'équipe d'un joueur (Possible uniquement avant la fin des inscriptions)
| N°  | Action  | Statut  | Mise à jour  |
|---|---|---|---|
| 1  | Je peux modifier l'équipe du joueur  | exclu  | 11/01/2023 |
| 2  | Je renseigne l'équipe du joueur| exclu  | 11/01/2023  |


### Suppression des joueurs (Possible uniquement avant la fin des inscriptions)
| N°  | Action  | Statut  | Mise à jour  |
|---|---|---|---|
| 1  | Je peux supprimer des joueurs  | Oui en backend  | 11/01/2023  |
| 2  | Je sélectionne les joueurs à supprimer  | Oui en backend  | 11/01/2023  |
| 3  | Je clique sur le bouton "supprimer joueur(s)"  | Oui en backend  | 11/01/2023  |



## Gestion du tournoi
### Création du tournoi
| N°  | Action  | Statut  | Mise à jour  |
|---|---|---|---|
| 1  | Je peux créer un tournoi  | Oui  | 11/01/2023  |
| 2  | Je renseigne le nom, la date de début, la date de fin des inscriptions et la date de fin du tournoi  | Oui  | 11/01/2023  |

### Modification du tournoi
| N°  | Action  | Statut  | Mise à jour  |
|---|---|---|---|
| 1  | Je peux modifier un tournoi  | Oui sous condition  | 23/10/2022  |
| 2  | Je renseigne le nom, la date de début, la date de fin des inscriptions et la date de fin du tournoi  | Oui sous condition  | 11/01/2023  |

### Génération de la phase de poules
| N°  | Action  | Statut  | Mise à jour  |
|---|---|---|---|
| 1  | Je peux générer la phase de poules  | Oui  | 11/01/2023  |
| 2  | Je clique sur le bouton "générer les poules et matchs de poules" | Oui  | 11/01/2023  |


### Génération des tableaux finaux
| N°  | Action  | Statut  | Mise à jour  |
|---|---|---|---|
| 1  | Je peux générer les tableaux du tournoi  | Oui  | 11/01/2023  |
| 2  | Je clique sur le bouton "générer les tableaux et les matchs de tableaux"  | Oui  | 11/01/2023  |

### Gestion des matchs
| N°  | Action  | Statut  | Mise à jour  |
|---|---|---|---|
| 1  | Je peux modifier un match  | Oui sous conditions  | 11/01/2023  |
| 2  | Je renseigne le score de la première équipe et le score de la seconde  | Oui  | 11/01/2023  |
