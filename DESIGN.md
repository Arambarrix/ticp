# DIAGRAMME

## DIAGRAMME DE CAS D'UTILISATION

Nous avons deux acteurs (les utilisateurs et l'administrateur) qui interagissent avec le système

<img src="pictures/diagramme_de_cas_dutilisation.jpg" style="zoom:20%;" />

### LES ACTIONS QU'UN ADMINISTRATEUR PEUT EFFECTUER :
- GERER UN TOURNOI :
    - LANCER TOURNOI : une fois qu'on arrive à la date limite d'inscription, l'administrateur lance un et un seul tournoi.
    - MODIFIER TOURNOI : une fois qu'on arrive à la date limite d'inscription, l'administrateur lance un et un seul tournoi.
    - ANNULER TOURNOI : l'administrateur pourra annuler un tournoi dans certaines situations particulières(covid par exemplle)

-  GERER EQUIPE :
    -  MODIFIER EQUIPE : seul un administrateur peut disqualifier une équipe du tournoi, si celle-ci ne remplit les condition  d'éligibilité.

- GERER MATCH
    - LANCER MATCH : l'administrateur est capable de lancer des matchs entre équipe.


### LES ACTIONS QU'UN UTILISATEUR PEUT  EFFECTUER :


- GERER EQUIPE
    - MODIFIER EQUIPE : ce cas d'utilisation permet à l'utilisateur de mettre à jour son équipe : changer de nom de l'équipe et des membres

    - CREER EQUIPE : cette fonctionnalité permet à l'utilisateur de créer une équipe lors de  l'inscription en renseignant le nom 
de l'équipe et des joueurs qui la compose


- GERER MATCH
    - MODIFIER MATCH  : une fois que les équipes finissent leur match, cette action leur permettra de renseigner le  score du match

### LES REGLES DE GESTION :

| Règle de Gestion                                             |
| ------------------------------------------------------------ |
| les équipes sont formées de 2,3,4 personnes                  |
| Une seule poule est constituée de 3 ou 4 équipes             |
| on peut créer maxi 3 tableaux                                |
| 2 équipes d'une même poule ne doivent pas se rencontrer dès le 1er tour du tableau |
| Le 1er d'une poule doit rencontrer le 2ème d'une autre poule |
| 2 équipes d'une même poule doivent se rencontrer le plus tard possible |
| à la fin de la période d'inscription, les poules sont générées et on bloque la création des équipes |

## DIAGRAMME DE CLASSES

<img src="pictures/diagramme_de_classes.jpg" style="zoom:20%;" />

### DESCRIPTION DES  CLASSES

- TOURNOI : elle est caractérisée par un identifiant et six dates(date début d'inscription des équipes, date fin d'inscription des équipes, date début poule, date début tableau, date début tournoi, date fin tournoi)

- EQUIQUE : cette classe est caractérisée par un identifiant, un nom d'équipe, un état pour savoir si l'équipe est éliminée, disqualifiée...

- TABLEAU : elle possède un identifiant, un nom, et un rang

- MATCH : elle est caractérisée par un identifiant, une date qui sera renseignée par les équipes apres avoir  joué leurs matchs, et deux scores(renseignant le score des deux équipes s'affrontant)

- JOUEUR : cette classe possède un identifiant et un nom complet du  joueur

- POULE : Cette classe est caractérisée par un identifiant et un nom




# MAQUETTE

## FONCTIONNALITES PRINCIPALES

### PAGE ACCUEIL ( État initial)
Il s'agit de la page principale. C'est la page qui se présente à l'utilisateur lorsque le tournoi est en phase "d'inscription"

<img src="pictures/groupe1_tournoi_de_petanque_1.jpg" style="zoom:20%;" />

### PAGE D'INSCRIPTION
Il s'agit de la page D'inscription des équipes. C'est la page qui se présente à l'utilisateur lorsqu'il souhaite inscrire son équipe au tournoi.

<img src="pictures/groupe1_tournoi_de_petanque_2.jpg" style="zoom:20%;" />

### PAGE D'ACCUEIL ( État Poule générée )
Il s'agit de la page principale. C'est la page qui se présente à l'utilisateur lorsque les inscriptions sont cloturées. Cette page présente les Poules constituées de Matchs.

<img src="pictures/groupe1_tournoi_de_petanque_8.jpg" style="zoom:20%;" />

<img src="pictures/groupe1_tournoi_de_petanque_7.jpg" style="zoom:20%;" />

### PAGE DES  TABLEAUX 
Il s'agit de la page qui présente les matchs de chaque Tableau. Ces tableaux sont générés après la fin des Matchs de Poule.

<img src="pictures/groupe1_tournoi_de_petanque_6.jpg" style="zoom:20%;" />

<img src="pictures/groupe1_tournoi_de_petanque_5.jpg" style="zoom:20%;" />

## FONCTIONNALITES SECONDAIRES

### PAGE HISTORIQUES
Il s'agit de la page qui récapitule l'ensemble des tournois, matchs, équipes, poules, vainqueurs qui ont existé jusqu'à aujourd'hui.

<img src="pictures/groupe1_tournoi_de_petanque_3.jpg" style="zoom:20%;" />

<img src="pictures/groupe1_tournoi_de_petanque_4.jpg" style="zoom:20%;" />


### PAGE PHOTOS
Il s'agit de la page qui récapitule l'ensemble des tournois, matchs, équipes, poules, vainqueurs qui ont existé jusqu'à aujourd'hui.

<img src="pictures/groupe1_tournoi_de_petanque_9.jpg" style="zoom:20%;" />

### PAGE INFORMATIONS UTILES
Il s'agit de la page qui rappelle les règles du concours, les emplacements où se joue les matchs, les actualités et les échéances à respecter.

<img src="pictures/groupe1_tournoi_de_petanque_10.jpg" style="zoom:20%;" />
