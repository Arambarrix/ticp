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
            Tableau update = repository.getReferenceById(tableau.getId());
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
        for(MatchTableau matchTableau:matchTableauList){
            rounds.add(matchTableau.getTour());
        }
        for(int i=0;i< rounds.size();i++){
            if(Collections.frequency(rounds,i)>1){
                rounds.remove(i);
            }
        }
        Map<Integer, Map<Equipe, MatchTableau>> vainqueursRounds = new HashMap<>();
        List<Equipe> offices = new ArrayList<>();
        for(MatchTableau matchTableau:matchTableauList){
            //System.out.println("Tableau "+matchTableau.getTableau().getId());
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
                //System.out.println("Tableau "+matchTableau.getTableau().getId());
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
                    //System.out.println("Tableau "+matchTableau.getTableau().getId());
                    //System.out.println("Pour le match "+matchTableau.getId());
                    if(matchTableau.getTableau().getId()==id && equipe!=matchTableauRepository.findEquipeByMatchInTour(equipe.getId(), val.getKey()+1) & matchTableau.getTour()< rounds.size()-1 & matchTableau.getEquipeA()==null){
                        //System.out.println("Non, il n'y a pas de qualifié en position A dans ce match prévu") ;
                        matchTableau.setEquipeA(equipe);
                        matchTableauRepository.save(matchTableau);
                    }
                    else if(matchTableau.getTableau().getId()==id && equipe!=matchTableauRepository.findEquipeByMatchInTour(equipe.getId(), val.getKey()+1) & matchTableau.getTour()< rounds.size()-1 & !(val.getValue().containsKey(matchTableau.getEquipeA())||offices.contains(matchTableau.getEquipeA()))){
                        //System.out.println("Non, il n'y a pas de qualifié d'office en position A dans ce match prévu") ;
                        matchTableau.setEquipeA(equipe);
                        matchTableauRepository.save(matchTableau);

                    }
                }
            }
            for(Equipe equipe:val.getValue().keySet()){
                for(MatchTableau matchTableau: matchTableauRepository.findMatchsByTours(val.getKey()+1)){
                    //System.out.println("Tableau "+matchTableau.getTableau().getId());
                    //System.out.println("Pour le match "+matchTableau.getId());
                    if(matchTableau.getTableau().getId()==id && equipe!=matchTableauRepository.findEquipeByMatchInTour(equipe.getId(), val.getKey()+1) & matchTableau.getTour()< rounds.size()-1 & matchTableau.getEquipeB()==null){
                        //System.out.println("Non, il n'y a pas de qualifié en position B dans ce match prévu") ;
                        matchTableau.setEquipeB(equipe);
                        matchTableauRepository.save(matchTableau);
                    }
                    else if(matchTableau.getTableau().getId()==id && equipe!=matchTableauRepository.findEquipeByMatchInTour(equipe.getId(), val.getKey()+1) & matchTableau.getTour()< rounds.size()-1 & !(val.getValue().containsKey(matchTableau.getEquipeB())||offices.contains(matchTableau.getEquipeB()))){
                        //System.out.println("Non, il n'y a pas de qualifié d'office en position B dans ce match prévu") ;
                        matchTableau.setEquipeB(equipe);
                        matchTableauRepository.save(matchTableau);

                    }
                }
            }
            for(MatchTableau matchTableau: matchTableauRepository.findMatchsByTours(val.getKey()+1)){
                //System.out.println("Tableau "+matchTableau.getTableau().getId());
                //System.out.println("Pour le match "+matchTableau.getId());
                //System.out.println(!(val.getValue().containsKey(matchTableau.getEquipeA())||offices.contains(matchTableau.getEquipeA())));
                //System.out.println(!(val.getValue().containsKey(matchTableau.getEquipeB())||offices.contains(matchTableau.getEquipeB())));
                if(matchTableau.getTableau().getId()==id && matchTableau.getTour()< rounds.size()-1 & !(val.getValue().containsKey(matchTableau.getEquipeA())||offices.contains(matchTableau.getEquipeA()))){
                    //System.out.println("L'équipe en position A n'est pas qualifiée d'office, ni méritante");
                    matchTableau.setEquipeA(null);
                    matchTableau.setScoreA(0);
                    matchTableauRepository.save(matchTableau);
                }
                if(matchTableau.getTableau().getId()==id && matchTableau.getTour()< rounds.size()-1 & !(val.getValue().containsKey(matchTableau.getEquipeB())||offices.contains(matchTableau.getEquipeB()))) {
                    //System.out.println("L'équipe en position B n'est pas qualifiée, ni méritante");
                    matchTableau.setEquipeB(null);
                    matchTableau.setScoreB(0);
                    matchTableauRepository.save(matchTableau);
                }
            }
        }
    }

    public List<Tableau> getTableaux() {
        return repository.findAll();
    }
}

