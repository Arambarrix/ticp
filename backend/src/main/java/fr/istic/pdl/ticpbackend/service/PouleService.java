package fr.istic.pdl.ticpbackend.service;

import fr.istic.pdl.ticpbackend.model.Equipe;
import fr.istic.pdl.ticpbackend.model.MatchPoule;
import fr.istic.pdl.ticpbackend.model.Poule;
import fr.istic.pdl.ticpbackend.repository.PouleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.Optional;
import java.util.*;

/**
 * Ce service permet de créer les fonctionnalités des poules
 */
@Service
@AllArgsConstructor
public class PouleService {
    PouleRepository repository;
    public Optional<Poule> getPoule(Long id){
        return repository.findById(id);
    }
    public void savePoule(Poule poule){
        if(repository.existsById(poule.getId())){
            Poule update = repository.getReferenceById(poule.getId());
            update.setNom(poule.getNom());
            repository.save(poule);
        }

    }
    public void deletePoule(Long id){
        repository.deleteById(id);
    }

    public List<Equipe> getAllTeams(Long id){
        return repository.findAllTeamsByPouleQuery(id);
    }

    /**
     * Cette méthode permet de faire le classement d'une poule.
     * @param id est l'identifiant de la poule à classer
     */
    public List<Equipe> getRanking(Long id){
        List<MatchPoule> matchPoules = repository.findMatchsPoulesByPoule(id);
        List<Equipe> equipes = repository.findAllTeamsByPouleQuery(id);
        if(!repository.existsById(id)){
            try {
                throw new RuntimeException("Poule inexistante");
            } catch (Exception e) {
            }
        }
        else{

            Map<Equipe,List<MatchPoule>> victoiresEquipes = new HashMap<>();
            Map<Equipe,Integer> pointsEquipes = new HashMap<>();
            for(Equipe equipe:equipes){
                List<MatchPoule> victoires = new ArrayList<>();
                List<Integer> points = new ArrayList<>();
                for(MatchPoule matchPoule:matchPoules){
                    if((matchPoule.getScoreA()>matchPoule.getScoreB()) && matchPoule.getEquipeA().equals(equipe)){
                        victoires.add(matchPoule);
                    }
                    else if((matchPoule.getScoreA()<matchPoule.getScoreB()) && matchPoule.getEquipeB().equals(equipe)){
                        victoires.add(matchPoule);
                    }
                }
                for(MatchPoule matchPoule:matchPoules){
                    if(matchPoule.getEquipeA().equals(equipe)){
                        points.add(matchPoule.getScoreA()-matchPoule.getScoreB());
                    }
                    else if(matchPoule.getEquipeB().equals(equipe)){
                        points.add(matchPoule.getScoreB()-matchPoule.getScoreA());
                    }
                }
                pointsEquipes.put(equipe,points.stream().mapToInt(Integer::valueOf).sum());
                victoiresEquipes.put(equipe,victoires);
            }
            for (Map.Entry<Equipe,List<MatchPoule>>val:victoiresEquipes.entrySet()){
                System.out.println(val.getKey().getId()+" a remporté "+val.getValue().size());
            }
            for (Map.Entry<Equipe,Integer>val:pointsEquipes.entrySet()){
                System.out.println(val.getKey().getId()+" a une différence de "+val.getValue());
            }
            equipes.sort((o1, o2) -> {
                int value = victoiresEquipes.get(o2).size() - victoiresEquipes.get(o1).size();
                if (value == 0) {
                    value = pointsEquipes.get(o2).compareTo(pointsEquipes.get(o1));
                }
                return value;
            });
            for(int i=0;i<equipes.size();i++){
                System.out.println((i+1)+"-"+equipes.get(i).getId()+"-"+victoiresEquipes.get(equipes.get(i)).size()+"-"+pointsEquipes.get(equipes.get(i)));

            }

        }
        return equipes;

    }
    public List<Poule> getPoules(){
        return repository.findAll();
    }
}
