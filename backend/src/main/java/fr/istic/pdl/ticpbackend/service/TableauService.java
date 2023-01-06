package fr.istic.pdl.ticpbackend.service;

import fr.istic.pdl.ticpbackend.model.Equipe;
import fr.istic.pdl.ticpbackend.model.MatchTableau;
import fr.istic.pdl.ticpbackend.model.Tableau;
import fr.istic.pdl.ticpbackend.repository.MatchTableauRepository;
import fr.istic.pdl.ticpbackend.repository.TableauRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
/**
 * Ce service permet de créer les fonctionnalités des tableaux
 */
@Service
@AllArgsConstructor
public class TableauService {
    TableauRepository repository;
    MatchTableauRepository matchTableauRepository;

    /**
     * Récupère un tableau
     * @param id l'identifiant du tableau
     * @return un tableau
     */
    public Tableau getTableau(Long id) {
        return repository.findById(id).get();
    }

    /**
     * Met à jour un tableau
     * @param tableau à mettre à jour
     * @throws RuntimeException si le tableau n'existe pas
     */
    public void updateTableau(Tableau tableau) {
        if(repository.existsById(tableau.getId())) {
            Tableau update = repository.findById(tableau.getId()).get();
            update.setNom(tableau.getNom());
            repository.save(update);
        }
        else{
            throw new RuntimeException("Tableau inexistant");
        }
    }

    /**
     * Supprime un tableau
     * @param id l'identifiant du tableau
     */

    public void deleteTableau(Long id) {
        repository.deleteById(id);
    }

    public List<MatchTableau> getMatchsTableau(Long id){
        return repository.getReferenceById(id).getListMatchs();
    }

    /**
     * Génère la progression des équipes dans un tableau
     * @param id l'identifiant du tableau
     */
    public void nextRound(Long id) {

        List<MatchTableau> matchTableauList = repository.findMatchsTableaux(id);
        List<Integer> rounds = new ArrayList<>();
        Map<Integer, Map<Equipe, MatchTableau>> vainqueursRounds = new HashMap<>();
        List<Equipe> qualifiesFinale = new ArrayList<>();
        List<Equipe> offices = new ArrayList<>();
        for(MatchTableau matchTableau:matchTableauList){
            rounds.add(matchTableau.getTour());
        }
        for(int i=0;i< rounds.size();i++){
            if(Collections.frequency(rounds,i)>1){
                rounds.remove(i);
            }
        }
        //Mise à jour de la progression jusqu'aux demi-finales
        for(MatchTableau matchTableau:matchTableauList){
            if(matchTableau.getTour()==1 & matchTableau.getEquipeA()!=null){
                if(matchTableau.getEquipeA() != matchTableauRepository.findEquipeByMatchInTour(matchTableau.getEquipeA().getId(),matchTableau.getTour()-1)){
                    offices.add(matchTableau.getEquipeA());
                }
            }
            if(matchTableau.getTour()==1 & matchTableau.getEquipeB()!=null){
                if(matchTableau.getEquipeB() != matchTableauRepository.findEquipeByMatchInTour(matchTableau.getEquipeB().getId(),matchTableau.getTour()-1)){
                    offices.add(matchTableau.getEquipeB());
                }
            }
        }

        for (int i = 0; i < rounds.size(); i++) {
            Map<Equipe, MatchTableau> vainqueurs = new HashMap<>();
            for (MatchTableau matchTableau : matchTableauList) {
                if (matchTableau.getTour() == i & (matchTableau.getEquipeA() != null || matchTableau.getEquipeB() != null)) {
                    if (matchTableau.getScoreA() > matchTableau.getScoreB()) {
                        vainqueurs.put(matchTableau.getEquipeA(), matchTableau);
                    } else if (matchTableau.getScoreA() < matchTableau.getScoreB()) {
                        vainqueurs.put(matchTableau.getEquipeB(), matchTableau);
                    }
                }
            }
            vainqueursRounds.put(i, vainqueurs);
        }

        for (Map.Entry<Integer, Map<Equipe, MatchTableau>> val : vainqueursRounds.entrySet()) {
            for(Equipe equipe:val.getValue().keySet()){
                for(MatchTableau matchTableau: matchTableauRepository.findMatchsByTours(val.getKey()+1)){
                    if(matchTableau.getTableau().getId()==id && equipe!=matchTableauRepository.findEquipeByMatchInTour(equipe.getId(), val.getKey()+1) & matchTableau.getTour()< rounds.size()-1 & matchTableau.getEquipeA()==null){
                        matchTableau.setEquipeA(equipe);
                        matchTableauRepository.save(matchTableau);
                    }
                    else if(matchTableau.getTableau().getId()==id && equipe!=matchTableauRepository.findEquipeByMatchInTour(equipe.getId(), val.getKey()+1) & matchTableau.getTour()< rounds.size()-1 & !(val.getValue().containsKey(matchTableau.getEquipeA())||offices.contains(matchTableau.getEquipeA()))){
                        matchTableau.setEquipeA(equipe);
                        matchTableauRepository.save(matchTableau);

                    }
                }
            }
            for(Equipe equipe:val.getValue().keySet()){
                for(MatchTableau matchTableau: matchTableauRepository.findMatchsByTours(val.getKey()+1)){
                    if(matchTableau.getTableau().getId()==id && equipe!=matchTableauRepository.findEquipeByMatchInTour(equipe.getId(), val.getKey()+1) & matchTableau.getTour()< rounds.size()-1 & matchTableau.getEquipeB()==null){
                        matchTableau.setEquipeB(equipe);
                        matchTableauRepository.save(matchTableau);
                    }
                    else if(matchTableau.getTableau().getId()==id && equipe!=matchTableauRepository.findEquipeByMatchInTour(equipe.getId(), val.getKey()+1) & matchTableau.getTour()< rounds.size()-1 & !(val.getValue().containsKey(matchTableau.getEquipeB())||offices.contains(matchTableau.getEquipeB()))){
                        matchTableau.setEquipeB(equipe);
                        matchTableauRepository.save(matchTableau);

                    }
                }
            }
            for(MatchTableau matchTableau: matchTableauRepository.findMatchsByTours(val.getKey()+1)){
                if(matchTableau.getTableau().getId()==id && matchTableau.getTour()< rounds.size()-1 & !(val.getValue().containsKey(matchTableau.getEquipeA())||offices.contains(matchTableau.getEquipeA()))){
                    matchTableau.setEquipeA(null);
                    matchTableau.setScoreA(0);
                    matchTableauRepository.save(matchTableau);
                }
                if(matchTableau.getTableau().getId()==id && matchTableau.getTour()< rounds.size()-1 & !(val.getValue().containsKey(matchTableau.getEquipeB())||offices.contains(matchTableau.getEquipeB()))) {
                    matchTableau.setEquipeB(null);
                    matchTableau.setScoreB(0);
                    matchTableauRepository.save(matchTableau);
                }
            }
        }
        //Mise à jour de la finale d'un tableau
        for (MatchTableau matchTableau : matchTableauList) {
            if (matchTableau.getTableau().getId()==id & matchTableau.getTour() == rounds.size()-2 & (matchTableau.getEquipeA() != null || matchTableau.getEquipeB() != null)) {
                if (matchTableau.getScoreA() > matchTableau.getScoreB()) {
                    qualifiesFinale.add(matchTableau.getEquipeA());
                } else if (matchTableau.getScoreA() < matchTableau.getScoreB()) {
                    qualifiesFinale.add(matchTableau.getEquipeB());
                }
            }
        }

        if(qualifiesFinale.size()<2){
            throw new RuntimeException("Il faudra attendre le résultat des deux matchs pour créer la finale");
        }
        else{
            List<MatchTableau> finales = matchTableauRepository.findMatchsByTours((rounds.size())-1);
            for(MatchTableau matchTableau:finales){
                if(matchTableau.getTableau().getId()==id){
                    matchTableau.setEquipeA(qualifiesFinale.get(0));
                    matchTableau.setEquipeB(qualifiesFinale.get(1));
                    matchTableauRepository.save(matchTableau);
                }
            }
        }

    }

    public List<Tableau> getTableaux() {
        return repository.findAll();
    }
}

