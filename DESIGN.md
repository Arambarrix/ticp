DIAGRAMME DE CLASSE

DESCRIPTION DES  CLASSES

CLASSE TOURNOI : elle est caracterisée par un identifiant et trois dates(date début du tournoi, date d'inscription des équipes(date début inscription et limite d'inscription), date fin du tournoi)

CLASSE EQUIQUE : cette classe est caracterisée par un identifiant, un nom d'équipe, un etat pour savoir si l'équipe est éliminée, disqualifiée etc.

CLASSE TABLEAU : elle posséde un identifiant, un nom, et un rang

CLASSE MATCH : elle est caracterisée par un identifiant, une date(data à laquelle le match jouera), et deux scores(renseignant le score des deux équipes s'enfrontant)

CLASSE JOUEUR : cette posséde un identifiant et un nom complet du  joueur

CLASSE POULE : caracterisée par un identifiant et un nom


DIAGRAMME DE CAS D'UTILISATION

Nous avons deux acteurs(les utilisateurs et l'administrateur) qui interagissent avec le système 

LES ACTIONS QUE PEUVENT REALISER UN ADMINISTRATEUR :

CREER TOURNOI : une fois qu'on arrive à la date limite d'inscription, l'administrateur lance un et un seul tournoi

MODIFIER EQUIPE : seul un administrateur peut disqualifier une équipe du tournoi, si celle-ci ne remplit les condition  d'éligibilité

CREER MATCH : l'administrateur est capable de créer un match entre équipe

LES ACTIONS QUE LES UTILISATEURS QUE PEUVENT  EFFECTUER :

MODIFIER EQUIPE : ce cas d'utilisation permet à l'utilisateur de mettre à jour son équipe par exemple  : changer de nom

CREER EQUIPE : cette fonctionnalité permet à l'utilisateur de créer une équipe de  l'inscription en renseignant le nom 
de l'équipe et des joueurs qui la compose

MODIFIER MATCH  : une fois que les équipes finissent leur match, cette action leur permettra de renseigner le  score du match
