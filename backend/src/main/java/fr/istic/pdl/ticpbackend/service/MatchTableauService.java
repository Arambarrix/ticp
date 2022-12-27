package fr.istic.pdl.ticpbackend.service;

import fr.istic.pdl.ticpbackend.model.MatchPoule;
import fr.istic.pdl.ticpbackend.model.MatchTableau;
import fr.istic.pdl.ticpbackend.repository.MatchTableauRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Ce service implémente les fonctionnalités des matchs de tableau
 */
@Service
@AllArgsConstructor
public class MatchTableauService {
    MatchTableauRepository repository;

    /**
     * Permet de retourner un match de tableau
     * @param id l'identifiant du match de tableau
     * @return un match de tableau s'il existe
     *
     */
    public Optional<MatchTableau> getMatchTableau(Long id){
        return repository.findById(id);
    }

    /**
     * Permet de mettre à jour un match de tableay
     * @param match le match de tableau à mettre à jour
     */
    public void updateMatchTableau(MatchTableau match){
        if(repository.existsById(match.getId())){
            MatchTableau matchTableau = repository.getReferenceById(match.getId());
            matchTableau.setScoreA(match.getScoreA());
            matchTableau.setScoreB(match.getScoreB());
            matchTableau.setLieu(match.getLieu());
            repository.save(matchTableau);
        }
        else {
            throw new RuntimeException("Match inexistant");
        }
    }

    /**
     * Permet de supprimer un match de tableau
     * @param id du match à supprimer
     */
    public void deleteMatchTableau(Long id){
        repository.deleteById(id);
    }

    /**
     * Permet de retourner tous les matchs de tableau du tournoi
     * @return la liste des matchs de tableau du tournoi
     */
    public List<MatchTableau> getMatchsTableaux() {
        return repository.findAll();
    }
}
