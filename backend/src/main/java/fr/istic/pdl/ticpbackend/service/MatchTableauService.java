package fr.istic.pdl.ticpbackend.service;

import fr.istic.pdl.ticpbackend.model.MatchTableau;
import fr.istic.pdl.ticpbackend.model.Tableau;
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
    TableauService tableauService;

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
    public void updateMatchTableau(Long id, MatchTableau match){
        if(!repository.existsById(id)){
            throw new RuntimeException("Match inexistant");
        }
        else if((repository.getTournoi(id).getDateDebutTableau()==null)&(repository.getTournoi(id).getDateFinTournoi()==null || repository.getTournoi(id).getDateFinTournoi()!=null)){
            throw new RuntimeException("Modification interdite tant que le date du début des tableaux n'est pas définie");
        }
        else if(LocalDate.now().isAfter(repository.getTournoi(id).getDateFinTournoi()) || LocalDate.now().isBefore(repository.getTournoi(id).getDateDebutTableau())){
            throw new RuntimeException("Modification interdite en dehors des dates réglementaires. Veuillez consulter les dates du tableau.");
        }
        else {
            System.out.println(repository.findById(id).get());
            MatchTableau matchTableau = repository.getReferenceById(id);
            matchTableau.setScoreA(match.getScoreA());
            matchTableau.setScoreB(match.getScoreB());
            if(match.getLieu() != null) {
                matchTableau.setLieu(match.getLieu());
            }
            repository.save(matchTableau);
            List<Integer> rounds = new ArrayList<>();
            for(MatchTableau matchTableauTour:tableauService.getMatchsTableau(matchTableau.getTableau().getId())){
                rounds.add(matchTableauTour.getTour());
            }
            for(int i=0;i< rounds.size();i++){
                while(Collections.frequency(rounds,i)>1){
                    rounds.remove(i);
                }
            }
            if(matchTableau.getTour()!=rounds.size()-1) {
                tableauService.nextRound(matchTableau.getTableau().getId());
            }
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
