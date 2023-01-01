package fr.istic.pdl.ticpbackend.service;

import fr.istic.pdl.ticpbackend.model.Equipe;
import fr.istic.pdl.ticpbackend.model.Joueur;
import fr.istic.pdl.ticpbackend.repository.JoueurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Ce service définit les fonctions propres aux joueurs
 */

@Service
@AllArgsConstructor
public class JoueurService {
    JoueurRepository repository;

    /**
     * Récupère tous les joueurs du tournoi
     * @return tous les joueurs du tournoi
     */
    public List<Joueur> getJoueurs(){
        return repository.findAll();
    }

    /**
     * Retourne un joueur
     * @param id l'identifiant d'un joueur
     * @return un joueur, s'il existe
     */
    public Optional<Joueur> getJoueur(Long id){
        return repository.findById(id);
    }

    /**
     * Enregistre un joueur
     * @param joueur à enregistrer
     */
    public void saveJoueur(Joueur joueur){
        repository.save(joueur);
    }

    /**
     * Supprime un joueur
     * @param id l'identifiant du joueur à supprimer
     */
    public void deleteJoueur(Long id){
        repository.deleteById(id);
    }

    /**
     * Met à jour un joueur
     * @param joueur à mettre à jour
     * @throws RuntimeException si le joueur n'a pas d'équipe ou l'équipe est pleine (3 joueurs max)
     */
    public void updateJoueur(Joueur joueur){
        if(repository.existsById(joueur.getId())){
            Joueur update = repository.getReferenceById(joueur.getId());
            update.setNom(joueur.getNom());
            if(joueur.getEquipe()!=null & joueur.getEquipe().getJoueurs().size()<3){
                update.setEquipe(joueur.getEquipe());
                repository.save(update);
            }
            else if(joueur.getEquipe()==null){
                throw new RuntimeException("Equipe manquante !");
            }
            else if(joueur.getEquipe().getJoueurs().size()>=3){
                throw new RuntimeException("Equipe pleine !");
            }
        }
    }
    public Equipe getEquipe(Long id){
        return repository.getReferenceById(id).getEquipe();
    }
}
