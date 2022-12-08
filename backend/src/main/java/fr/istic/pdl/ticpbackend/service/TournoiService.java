package fr.istic.pdl.ticpbackend.service;

import fr.istic.pdl.ticpbackend.model.*;
import fr.istic.pdl.ticpbackend.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import java.util.*;

@Service
@AllArgsConstructor
public class TournoiService {
    TournoiRepository repository;
    PouleRepository pouleRepository;
    TableauRepository tableauRepository;
    MatchPouleRepository matchPouleRepository;
    MatchTableauRepository matchTableauRepository;

    public Tournoi getTournoi(int id){
        return repository.findById(new Long(id)).get();
    }

    public void saveTournoi(Tournoi tournoi) throws Exception {
        if(repository.findAll().size()==1 && !repository.existsById(tournoi.getId())){
            throw new Exception("Il y a déjà un tournoi en cours !");
        }
        repository.save(tournoi);
    }

    public void createGroupes(Tournoi tournoi){
        Tournoi update = repository.getReferenceById(tournoi.getId());
        int nbEquipes = update.tailleTournoi();

        if(nbEquipes>5){
            List<Equipe> allTeams = update.getEquipes();
            List<Poule> poules = new ArrayList<>();
            int nbGroupes = (int)Math.ceil(((double)nbEquipes/4));
            int nombreQuatre=0;
            int nombreTrois=0;
            List<Poule> groupeQuatre = new ArrayList<>();
            List<Poule> groupeTrois = new ArrayList<>();
            if(nbEquipes==5) {
                try {
                    throw new Exception("Tournoi impossible. Veuillez rajouter ou enlever une équipe !");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else{
                if(nbEquipes<18){
                    for(int i=0;i<=(nbEquipes)/4;i++){
                        for(int j=0;j<=(nbEquipes)/3;j++){
                            if(4*i+3*j==nbEquipes){
                                nombreQuatre=i;
                                nombreTrois=j;
                            }
                        }
                    }
                }
                else{
                    for(int i=0;i<=(nbEquipes)/4;i++){
                        for(int j=0;j<=i;j++){
                            if(4*i+3*j==nbEquipes){
                                nombreQuatre=i;
                                nombreTrois=j;
                            }
                        }
                    }
                }
            }

                for(int i=0;i<nbGroupes;i++) {
                    Poule poule = new Poule();
                    poule.setId((long) i);
                    poule.setNom("Groupe " + (i+1));
                    poules.add(poule);
                    //pouleRepository.save(poule);
                }

                HashMap<Poule, List<Equipe>> listHashMap = new HashMap<>();
                if(nombreQuatre>nombreTrois){
                    for(Poule groupe: poules) {
                        listHashMap.put(groupe, new ArrayList<>());
                        for (int j = 0; j < 4; j++) {
                            if (!allTeams.isEmpty()) {
                                Equipe equipe = allTeams.get(new Random().nextInt(allTeams.size()));
                                listHashMap.get(groupe).add(equipe);
                                allTeams.remove(equipe);
                            }
                        }
                        pouleRepository.save(groupe);
                    }
                }
                else if(nombreQuatre<=nombreTrois){
                    for(int i=0;i<nombreQuatre;i++){
                        Poule poule = new Poule();
                        poule.setId((long)i+1);
                        poule.setNom("Groupe "+(i+1));
                        groupeQuatre.add(poule);
                    }
                    for(int i=0;i<nombreTrois;i++){
                        Poule poule = new Poule();
                        poule.setId((long)i+nombreQuatre+1);
                        poule.setNom("Groupe "+(i+nombreQuatre+1));
                        groupeTrois.add(poule);
                    }

                    for (Poule groupeQ:groupeQuatre) {
                        listHashMap.put(groupeQ,new ArrayList<>());
                        for (int j = 0; j < 4; j++) {
                            if (!allTeams.isEmpty()) {
                                Equipe equipe = allTeams.get(new Random().nextInt(allTeams.size()));
                                listHashMap.get(groupeQ).add(equipe);
                                allTeams.remove(equipe);
                            }
                        }
                        pouleRepository.save(groupeQ);
                    }
                    for(Poule groupeT:groupeTrois){
                        listHashMap.put(groupeT,new ArrayList<>());
                        for (int j = 0; j < 3; j++) {
                            if (!allTeams.isEmpty()) {
                                Equipe equipe = allTeams.get(new Random().nextInt(allTeams.size()));
                                listHashMap.get(groupeT).add(equipe);
                                allTeams.remove(equipe);
                            }
                        }
                        pouleRepository.save(groupeT);
                    }
                    poules=groupeQuatre;
                    poules.addAll(groupeTrois);
                }
                for(Poule groupe: poules){
                    for(int i=0;i<listHashMap.get(groupe).size()-1;i++){
                        for(int j=i+1;j<listHashMap.get(groupe).size();j++){
                            MatchPoule match = new MatchPoule(groupe);
                            match.setEquipeA(listHashMap.get(groupe).get(i));
                            match.setEquipeB(listHashMap.get(groupe).get(j));
                            match.setLieu("Inconnu");
                            groupe.getListMatchs().add(match);
                            matchPouleRepository.save(match);
                        }
                    }
                }
            }
        }
    public void deleteTournoi(){
        repository.deleteAll();
        pouleRepository.deleteAll();
        tableauRepository.deleteAll();
    }

    public void createTableaux(Tournoi tournoi) {
    }
}
