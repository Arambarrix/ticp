package fr.istic.pdl.ticpbackend.service;

import fr.istic.pdl.ticpbackend.model.*;
import fr.istic.pdl.ticpbackend.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Ce service définit les fonctions propres aux équipes
 */
@Service
@AllArgsConstructor
public class EquipeService {
    EquipeRepository repository;
    JoueurRepository joueurRepository;
    MatchPouleRepository matchPouleRepository;
    MatchTableauRepository matchTableauRepository;

    /**
     * Retourne une équipe dans le tournoi
     * @param id l'identifiant d'une équipe
     * @return une équipe
     */
    public Optional<Equipe> getEquipe(Long id){
        return repository.findById(id);
    }

    /**
     * Enregistre une équipe
     * @param equipe à enregistrer
     */
    public void saveEquipe(Equipe equipe){
        repository.save(equipe);
    }

    /**
     * Supprime une équipe du tournoi
     * @param id l'identifiant de l'équipe
     */
    public void deleteEquipe(Long id){
        repository.deleteById(id);
    }

    /**
     * Met à jour une équipe
     * @param equipe à mettre à jour
     */
    public void updateEquipe(Equipe equipe){
        if(repository.existsById(equipe.getId()))
        {
            Equipe update = repository.getReferenceById(equipe.getId());
            update.setNom(equipe.getNom());
            update.setJoueurs(equipe.getJoueurs());
            repository.save(update);
        }
    }

    /**
     * Retourne la poule d'une équipe
     * @param id l'identifiant de l'équipe
     * @return une poule
     */
    public Poule getPoule(Long id){
        return repository.findPoule(id);
    }

    /**
     * Retourne le tableau d'une équipe
     * @param id l'identifiant d'une équipe
     * @return un tableau
     */
    public Tableau getTableau(Long id){
        return repository.findTableau(id);
    }

    /**
     * Retourne les matchs de poule d'une équipe
     * @param id l'identifiant d'une équipe
     * @return liste des matchs de poule
     */
    public List<MatchPoule> getMatchsPoules(Long id){
        List<MatchPoule> matchPoules=new ArrayList<>();
        for(MatchPoule matchPoule:matchPouleRepository.findAll()){
            if(matchPoule.getEquipeA().getId()==id || matchPoule.getEquipeB().getId()==id){
                matchPoules.add(matchPoule);
            }
        }
        return matchPoules;
    }

    /**
     * Retourne la progression d'une équipe dans un tableau, implicitement un seul tableau
     * @param id l'identifiant d'une équipe
     * @return liste des matchs de tableau
     */
    public List<MatchTableau> getMatchsTableau(Long id){
        List<MatchTableau> matchsTableau=new ArrayList<>();
        for(MatchTableau matchTableau:matchTableauRepository.findAll()){
            if(matchTableau.getEquipeA().getId()==id || matchTableau.getEquipeB().getId()==id){
                matchsTableau.add(matchTableau);
            }
        }
        return matchsTableau;
    }

    /**
     * Retourne un match de poule d'une équipe
     * @param idEquipe l'identifiant de l'équipe
     * @param idMatch l'identifiant du match
     * @return un match de poule
     */
    public MatchPoule getMatchPoule(Long idEquipe, Long idMatch){
        if(matchPouleRepository.getReferenceById(idMatch).getEquipeA().getId()==idEquipe || matchPouleRepository.getReferenceById(idMatch).getEquipeB().getId()==idEquipe){
            return matchPouleRepository.getReferenceById(idMatch);
        }
        else{
            throw new RuntimeException("Match introuvable");
        }
    }

    /**
     * Retourne un match de tableau d'une équipe
     * @param idEquipe l'identifiant d'une équipe
     * @param idMatch l'identifiant d'un match
     * @return un match de tableau
     */
    public MatchTableau getMatchTableau(Long idEquipe, Long idMatch){
        if(matchTableauRepository.getReferenceById(idMatch).getEquipeA().getId()==idEquipe || matchTableauRepository.getReferenceById(idMatch).getEquipeB().getId()==idEquipe){
            return matchTableauRepository.getReferenceById(idMatch);
        }
        else{
            throw new RuntimeException("Match introuvable");
        }
    }

    /**
     * Permet de récupérer un joueur de l'équipe
     * @param idEquipe identifiant de l'équipe
     * @param idJoueur identifiant du joueur
     * @return le joueur cherché dans l'équipe
     * @throws RuntimeException si le joueur est inexistant dans l'équipe
     */
    public Joueur getJoueur(Long idEquipe, Long idJoueur){
        Joueur joueur=joueurRepository.findById(idJoueur).get();
        if(joueurRepository.findById(idJoueur).get().getEquipe().getId()!=idEquipe){
            throw new RuntimeException("Joueur inexistant dans l'équipe");
        }
        else{
            return joueur;
        }
    }
    public List<Joueur> getJoueurs(Long id){
        return repository.getReferenceById(id).getJoueurs();
    }
}
