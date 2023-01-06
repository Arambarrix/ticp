package fr.istic.pdl.ticpbackend.service;

import fr.istic.pdl.ticpbackend.model.MatchTableau;
import fr.istic.pdl.ticpbackend.repository.MatchTableauRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

/**
 * Ce service implémente les fonctionnalités des matchs de tableau
 */
@Service
@AllArgsConstructor
public class MatchTableauService {
    MatchTableauRepository repository;
    TournoiService tournoiService;

    /**
     * Permet de retourner un match de tableau
     * @param id l'identifiant du match de tableau
     * @return un match de tableau s'il existe
     *
     */
    public MatchTableau getMatchTableau(Long id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Match de tableau inexistant");
        }
        else{
            return repository.findById(id).get();
        }
    }


    /**
     * Permet de mettre à jour un match de tableay
     * @param match le match de tableau à mettre à jour
     * @throws RuntimeException si le match n'existe pas
     */
    public void updateMatchTableau(MatchTableau match){
        if(repository.existsById(match.getId()) & LocalDate.now().isBefore(repository.getTournoi(match.getId()).getDateFinTournoi()) & (LocalDate.now().isAfter(repository.getTournoi(match.getId()).getDateDebutTableau()))){
            MatchTableau matchTableau = repository.getReferenceById(match.getId());
            matchTableau.setScoreA(match.getScoreA());
            matchTableau.setScoreB(match.getScoreB());
            matchTableau.setLieu(match.getLieu());
            repository.save(matchTableau);
        }
        else if(!repository.existsById(match.getId())){
            throw new RuntimeException("Match inexistant");
        }
        else if(!(LocalDate.now().isBefore(repository.getTournoi(match.getId()).getDateFinTournoi()) & (LocalDate.now().isAfter(repository.getTournoi(match.getId()).getDateDebutTableau())))){
            throw new RuntimeException("Modification interdite en dehors des dates réglementaires. Veuillez consulter les dates du tableau.");
        }
    }

    /**
     * Permet de supprimer un match de tableau
     * @param id du match à supprimer
     */
    public void deleteMatchTableau(Long id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Match de tableau inexistant");
        }
        else{
            repository.deleteById(id);
        }
    }

    /**
     * Permet de retourner tous les matchs de tableau du tournoi
     * @return la liste des matchs de tableau du tournoi
     */
    public List<MatchTableau> getMatchsTableaux() {
        if(repository.findAll().isEmpty()){
            throw new RuntimeException("Aucun match tableau créé");
        }
        else{
            return repository.findAll();
        }
    }
}
