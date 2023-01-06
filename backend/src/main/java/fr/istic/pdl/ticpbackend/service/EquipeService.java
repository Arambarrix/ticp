package fr.istic.pdl.ticpbackend.service;

import fr.istic.pdl.ticpbackend.model.*;
import fr.istic.pdl.ticpbackend.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Ce service définit les fonctions propres aux équipes
 */
@Service
@AllArgsConstructor
public class EquipeService {
    EquipeRepository repository;
    JoueurRepository joueurRepository;
    MatchPouleRepository matchPouleRepository;
    MatchTableauRepository matchTableauRepository;
    TournoiRepository tournoiRepository;
    PouleRepository pouleRepository;
    TableauRepository tableauRepository;

    /**
     * Retourne une équipe dans le tournoi
     * @param id l'identifiant d'une équipe
     * @return une équipe
     */
    public Equipe getEquipe(Long id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Equipe inexistante");
        }
        else{
            return repository.findById(id).get();
        }
    }
    public List<Equipe> getEquipes(Long pageNo, Long pageSize){
        Pageable pageable = PageRequest.of(pageNo.intValue(),pageSize.intValue());
        if(repository.findAll(pageable).isEmpty()){
            throw new RuntimeException("Aucune équipe récupérable");
        }
        else {
            Page<Equipe> equipes = repository.findAll(pageable);
            List<Equipe> list = equipes.getContent();
            return list;
        }
    }

    /**
     * Enregistre une équipe
     * @param equipe à enregistrer
     */
    public void saveEquipe(Equipe equipe){
        if(tournoiRepository.findAll().isEmpty()){
            throw new RuntimeException("Impossible d'enregistrer une équipe sans tournoi");
        }
        else if(equipe.getTournoi()==null){
            throw new RuntimeException("Il faut obligatoirement un tournoi");
        }
        else {
            repository.save(equipe);
        }
    }

    /**
     * Supprime une équipe du tournoi
     * @param id l'identifiant de l'équipe
     */
    public void deleteEquipe(Long id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Equipe introuvable");
        }
        else{
            repository.deleteById(id);
        }
    }

    /**
     * Met à jour une équipe
     * @param equipe à mettre à jour
     */
    public void updateEquipe(Long id, Equipe equipe){
        if(repository.existsById(id))
        {
            Equipe update = repository.findById(id).get();
            update.setNom(equipe.getNom());
            update.setJoueurs(equipe.getJoueurs());
            repository.save(update);
        }
        else {
            throw new RuntimeException("Equipe introuvable");
        }
    }

    /**
     * Retourne la poule d'une équipe
     * @param id l'identifiant de l'équipe
     * @return une poule
     */
    public Poule getPoule(Long id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Equipe introuvable");
        }
        else if(!pouleRepository.existsById(repository.findPoule(id).getId())){
            throw new RuntimeException("Poule introuvable");
        }
        else{
            return repository.findPoule(id);
        }
    }

    /**
     * Retourne le tableau d'une équipe
     * @param id l'identifiant d'une équipe
     * @return un tableau
     */
    public Tableau getTableau(Long id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Equipe introuvable");
        }
        else if(!tableauRepository.existsById(repository.findTableau(id).getId())){
            throw new RuntimeException("Tableau introuvable");
        }
        else {
            return repository.findTableau(id);
        }
    }

    /**
     * Retourne les matchs de poule d'une équipe
     * @param id l'identifiant d'une équipe
     * @return liste des matchs de poule
     */
    public List<MatchPoule> getMatchsPoules(Long id){
        List<MatchPoule> matchPoules=new ArrayList<>();
        for(MatchPoule matchPoule:matchPouleRepository.findAll()){
            if(matchPoule.getEquipeA().getId()==id || matchPoule.getEquipeB().getId()==id){
                matchPoules.add(matchPoule);
            }
        }
        return matchPoules;
    }

    /**
     * Retourne la progression d'une équipe dans un tableau, implicitement un seul
     * @param id l'identifiant d'une équipe
     * @return liste des matchs de tableau d'une équipe
     */
    public List<MatchTableau> getMatchsTableau(Long id){
        List<MatchTableau> matchsTableau=new ArrayList<>();
        for(MatchTableau matchTableau:matchTableauRepository.findAll()){
            if(matchTableau.getEquipeA().getId()==id || matchTableau.getEquipeB().getId()==id){
                matchsTableau.add(matchTableau);
            }
        }
        if(matchsTableau.isEmpty()){
            throw new RuntimeException("Aucun match tableau disponible");
        }
        else {
            return matchsTableau;
        }
    }

    /**
     * Retourne un match de poule d'une équipe
     * @param idEquipe l'identifiant de l'équipe
     * @param idMatch l'identifiant du match
     * @return un match de poule
     */
    public MatchPoule getMatchPoule(Long idEquipe, Long idMatch){
        if(matchPouleRepository.getReferenceById(idMatch).getEquipeA().getId()==idEquipe || matchPouleRepository.getReferenceById(idMatch).getEquipeB().getId()==idEquipe){
            return matchPouleRepository.getReferenceById(idMatch);
        }
        else{
            throw new RuntimeException("Match introuvable");
        }
    }

    /**
     * Retourne un match de tableau d'une équipe
     * @param idEquipe l'identifiant d'une équipe
     * @param idMatch l'identifiant d'un match
     * @return un match de tableau
     */
    public MatchTableau getMatchTableau(Long idEquipe, Long idMatch){
        if(matchTableauRepository.getReferenceById(idMatch).getEquipeA().getId()==idEquipe || matchTableauRepository.getReferenceById(idMatch).getEquipeB().getId()==idEquipe){
            return matchTableauRepository.getReferenceById(idMatch);
        }
        else{
            throw new RuntimeException("Match introuvable");
        }
    }

    /**
     * Permet de récupérer un joueur de l'équipe
     * @param idEquipe identifiant de l'équipe
     * @param idJoueur identifiant du joueur
     * @return le joueur cherché dans l'équipe
     * @throws RuntimeException si le joueur est inexistant dans l'équipe
     */
    public Joueur getJoueur(Long idEquipe, Long idJoueur){
        Joueur joueur=joueurRepository.findById(idJoueur).get();
        if(joueurRepository.findById(idJoueur).get().getEquipe().getId()!=idEquipe){
            throw new RuntimeException("Joueur inexistant dans l'équipe");
        }
        else{
            return joueur;
        }
    }

    /**
     * Retourne la liste des joueurs d'une équipe
     * @param id identifiant de l'équipe
     * @return liste de joueurs
     */
    public List<Joueur> getJoueurs(Long id){
        return repository.getReferenceById(id).getJoueurs();
    }
}
