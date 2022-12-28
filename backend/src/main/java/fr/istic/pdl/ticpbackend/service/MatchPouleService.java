package fr.istic.pdl.ticpbackend.service;

import fr.istic.pdl.ticpbackend.model.Equipe;
import fr.istic.pdl.ticpbackend.model.MatchPoule;
import fr.istic.pdl.ticpbackend.repository.MatchPouleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Ce service implémente les fonctions propres aux matchs de poule
 */
@Service
@AllArgsConstructor
public class MatchPouleService {
    MatchPouleRepository repository;

    /**
     * Cette méthode retourne tous les matchs de poule du tournoi
     * @return la liste des matchs de poule du tournoi
     */
    public List<MatchPoule> getMatchsPoules(){
        return repository.findAll();
    }

    /**
     * Cette méthode retourne un match de poule
     * @param id l'identifiant d'un match de poule
     * @return Un match de poule
     */
    public Optional<MatchPoule> getMatchPoule(Long id){
        return repository.findById(id);
    }

    /**
     * Cette méthode permet de mettre à jour un match de poule
     * @param match que l'on doit mettre à jour
     * @throws RuntimeException si le match n'existe pas
     */
    public void updateMatchPoule(MatchPoule match){
        if(repository.existsById(match.getId())){
            MatchPoule matchPoule = repository.getReferenceById(match.getId());
            matchPoule.setScoreA(match.getScoreA());
            matchPoule.setScoreB(match.getScoreB());
            matchPoule.setLieu(match.getLieu());
            repository.save(matchPoule);
        }
        else {
            throw new RuntimeException("Match inexistant");
        }

    }

    /**
     * Supprime un match de poule
     * @param id du match à supprimer
     */
    public void deleteMatchPoule(Long id){
        repository.deleteById(id);
    }
}
