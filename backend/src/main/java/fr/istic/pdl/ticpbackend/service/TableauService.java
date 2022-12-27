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

    public Tableau getTableau(int id) {
        return repository.findById(new Long(id)).get();
    }

    public void updateTableau(Tableau tableau) {
        if(repository.existsById(tableau.getId())) {
            Tableau update = repository.getReferenceById(tableau.getId());
            update.setNom(tableau.getNom());
            repository.save(update);
        }
        else{
            throw new RuntimeException("Tableau inexistant");
        }
    }

    public void deleteTableau(Long id) {
        repository.deleteById(id);
    }

    /**
     * Cette méthode permet de générer la progression des équipes dans un tableau
     *
     * @param id
     */
    public void nextRound(Long id) {
        List<MatchTableau> matchTableauList = repository.findMatchsTableaux(id);
        List<Integer> rounds = matchTableauRepository.findTours();
        Map<Integer, Map<Equipe, MatchTableau>> vainqueursRounds = new HashMap<>();
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
                System.out.println(equipe+" déclarée vainqueur dans le round"+val.getKey());
            }
        }

        for (Map.Entry<Integer, Map<Equipe, MatchTableau>> val : vainqueursRounds.entrySet()) {
            for(Equipe equipe:val.getValue().keySet()){
                for(MatchTableau matchTableau: matchTableauRepository.findMatchsByTours(val.getKey()+1)){
                    if(equipe!=matchTableauRepository.findEquipeByMatchInTour(equipe.getId(), val.getKey()+1) & matchTableau.getTour()< rounds.size()-1 & matchTableau.getEquipeA()==null){
                        matchTableau.setEquipeA(equipe);
                        matchTableauRepository.save(matchTableau);
                    }
                    else if(equipe!=matchTableauRepository.findEquipeByMatchInTour(equipe.getId(), val.getKey()+1) & matchTableau.getTour()< rounds.size()-1 & !val.getValue().keySet().contains(matchTableau.getEquipeA())){
                        matchTableau.setEquipeA(equipe);
                        matchTableauRepository.save(matchTableau);

                    }
                }
            }
            for(Equipe equipe:val.getValue().keySet()){
                for(MatchTableau matchTableau: matchTableauRepository.findMatchsByTours(val.getKey()+1)){
                    if(equipe!=matchTableauRepository.findEquipeByMatchInTour(equipe.getId(), val.getKey()+1) & matchTableau.getTour()< rounds.size()-1 & matchTableau.getEquipeB()==null){
                        matchTableau.setEquipeB(equipe);
                        matchTableauRepository.save(matchTableau);
                    }
                    else if(equipe!=matchTableauRepository.findEquipeByMatchInTour(equipe.getId(), val.getKey()+1) & matchTableau.getTour()< rounds.size()-1 & !val.getValue().keySet().contains(matchTableau.getEquipeB())){
                        matchTableau.setEquipeB(equipe);
                        matchTableauRepository.save(matchTableau);
                    }
                }
            }
                for(MatchTableau matchTableau: matchTableauRepository.findMatchsByTours(val.getKey()+1)){
                    if(matchTableau.getTour()< rounds.size()-1 & (!val.getValue().containsKey(matchTableau.getEquipeA()))){
                        matchTableau.setEquipeA(null);
                        matchTableau.setScoreA(0);
                        matchTableauRepository.save(matchTableau);
                    }
                    if(matchTableau.getTour()< rounds.size()-1 & (!val.getValue().containsKey(matchTableau.getEquipeB()))){
                        matchTableau.setEquipeB(null);
                        matchTableau.setScoreB(0);
                        matchTableauRepository.save(matchTableau);
                    }
            }
        }
    }
}

