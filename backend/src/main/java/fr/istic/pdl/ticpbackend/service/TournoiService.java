package fr.istic.pdl.ticpbackend.service;

import fr.istic.pdl.ticpbackend.model.*;
import fr.istic.pdl.ticpbackend.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
/**
 * Ce service permet de créer les fonctionnalités du tournoi
 */
@Service
@AllArgsConstructor
public class TournoiService {
    TournoiRepository repository;
    PouleRepository pouleRepository;
    TableauRepository tableauRepository;
    MatchPouleRepository matchPouleRepository;
    MatchTableauRepository matchTableauRepository;

    public Tournoi getTournoi(){

        return repository.findAll().get(0);
    }

    public void saveTournoi(Tournoi tournoi) throws Exception {
        if(repository.findAll().size()==1 && !repository.existsById(tournoi.getId())){
            throw new Exception("Il y a déjà un tournoi "+tournoi.getNom()+" en cours !");
        }
        else {
            Tournoi update = repository.getReferenceById(tournoi.getId());
            update.setNom(tournoi.getNom());
            update.setDateDebutTournoi(tournoi.getDateDebutTournoi());
            if(tournoi.getDateDebutTournoi().isBefore(tournoi.getDateFinInscription())){
                update.setDateFinInscription(tournoi.getDateFinInscription());
                repository.save(update);

            }
            if(tournoi.getDateFinInscription().isBefore(tournoi.getDateDebutPoule())){
                update.setDateDebutPoule(tournoi.getDateDebutPoule());
                repository.save(update);

            }
            if(tournoi.getDateDebutPoule().isBefore(tournoi.getDateFinPoule())){
                update.setDateFinPoule(tournoi.getDateFinPoule());
                repository.save(update);

            }
            if(tournoi.getDateFinPoule().isBefore(tournoi.getDateDebutTableau())||tournoi.getDateFinPoule().isEqual(tournoi.getDateDebutTableau())){
                update.setDateDebutTableau(tournoi.getDateDebutTableau());
                repository.save(update);
            }
            if(tournoi.getDateDebutTableau().isBefore(tournoi.getDateFinTournoi())){
                update.setDateFinTournoi(tournoi.getDateFinTournoi());
                repository.save(update);
            }
            repository.save(update);
        }
    }

    /**
     * Permet de créer les groupes
     */
    public void createGroupes(){
        if(pouleRepository.findAll().size()>0){
            throw new RuntimeException("On ne peut créer de groupe.");
        }
        Tournoi update = repository.findAll().get(0);;
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

    /**
     * Permet de créer tous les tableaux après la phase de groupes
     */
    public void createTableaux() {
        if (tableauRepository.findAll().size() > 0) {
            throw new RuntimeException("On ne peut créer de tableau.");
        }

        List<Integer> puissances = new ArrayList<>();
        List<Tableau> tableaux = new ArrayList<>();
        List<Equipe> vainqueurs = new ArrayList<>();
        List<Equipe> seconds = new ArrayList<>();
        List<Equipe> troisiemes = new ArrayList<>();
        List<Equipe> quatriemes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            puissances.add((int) Math.pow(2, i));
        }
        PouleService service = new PouleService(pouleRepository);
        pouleRepository.findAll().forEach(groupe -> {
            List<Equipe> equipes = service.getRanking(groupe.getId());
            vainqueurs.add(equipes.get(0));
            seconds.add(equipes.get(1));
            troisiemes.add(equipes.get(2));
            if (equipes.size() >= 4) {
                quatriemes.add(equipes.get(3));
            }
        });
        int nbTableauxAutres = (int) Math.ceil(((double) (troisiemes.size() + quatriemes.size()) / 16));
        int totalPrincipal = vainqueurs.size() + seconds.size();
        for (int i = 0; i <= nbTableauxAutres; i++) {
            Tableau tableau = new Tableau();
            tableau.setId((long) i + 1);
            tableaux.add(tableau);
        }
        tableauRepository.saveAll(tableaux);

        /**
         * Tableau principal
         */
        int toursPrincipal = (int) Math.ceil(((Math.log(vainqueurs.size() + seconds.size()) / Math.log(2))));
        List<MatchTableau> matchTableaux = new ArrayList<>();
        for(int i=0;i<toursPrincipal;i++){
            for(int j=0;j<totalPrincipal/(int)Math.pow(2,i+1);j++) {
                MatchTableau match = new MatchTableau(tableaux.get(0),i);
                match.setTour(i);
                match.setLieu("Inconnu");
                matchTableaux.add(match);
                tableaux.get(0).setListMatchs(matchTableaux);

            }
        }
        if(puissances.contains(totalPrincipal)){
            for(MatchTableau matchTableau:matchTableaux){
                if(!vainqueurs.isEmpty()&matchTableau.getTour()==0){
                    Equipe premier = vainqueurs.get(new Random().nextInt(vainqueurs.size()));
                    Equipe deuxieme = seconds.get(new Random().nextInt(seconds.size()));
                    matchTableau.setEquipeA(premier);
                    matchTableau.setEquipeB(deuxieme);
                    tableaux.get(0).getListMatchs().set(tableaux.get(0).getListMatchs().indexOf(matchTableau),matchTableau);
                    vainqueurs.remove(premier);
                    seconds.remove(deuxieme);
                }
            }
        }
        else{
            if(totalPrincipal%2==0){

            }

        }
        matchTableauRepository.saveAll(matchTableaux);
    }

}
