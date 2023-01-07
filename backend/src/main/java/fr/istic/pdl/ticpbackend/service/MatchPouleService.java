package fr.istic.pdl.ticpbackend.service;

import fr.istic.pdl.ticpbackend.model.MatchPoule;
import fr.istic.pdl.ticpbackend.repository.MatchPouleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Ce service implémente les fonctions propres aux matchs de poule
 */
@Service
@AllArgsConstructor
public class MatchPouleService {
    MatchPouleRepository repository;
    TournoiService tournoiService;

    /**
     * Cette méthode retourne tous les matchs de poule du tournoi
     * @return la liste des matchs de poule du tournoi
     */
    public List<MatchPoule> getMatchsPoules(){
        if(repository.findAll().isEmpty()){
            throw new RuntimeException("Aucun match créé.");
        }
        else{
            return repository.findAll();
        }
    }

    /**
     * Cette méthode retourne un match de poule
     * @param id l'identifiant d'un match de poule
     * @return Un match de poule
     */
    public MatchPoule getMatchPoule(Long id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Match de poule introuvable");
        }
        else{
            return repository.findById(id).get();
        }
    }

    /**
     * Cette méthode permet de mettre à jour un match de poule
     * @param match que l'on doit mettre à jour
     * @throws RuntimeException si le match n'existe pas, si la phase de poules est terminée
     */
    public void updateMatchPoule(MatchPoule match){
        if(repository.existsById(match.getId()) & LocalDate.now().isBefore(repository.getTournoi(match.getId()).getDateFinPoule())){
            MatchPoule matchPoule = repository.getReferenceById(match.getId());
            matchPoule.setScoreA(match.getScoreA());
            matchPoule.setScoreB(match.getScoreB());
            matchPoule.setLieu(match.getLieu());
            repository.save(matchPoule);
        }
        else if(!repository.existsById(match.getId())){
            throw new RuntimeException("Match inexistant");
        }
        else if(LocalDate.now().isEqual(repository.getTournoi(match.getId()).getDateFinPoule()) || LocalDate.now().isAfter(repository.getTournoi(match.getId()).getDateFinPoule())){
            throw new RuntimeException("Phase de poules terminée");
        }

    }

    /**
     * Supprime un match de poule
     * @param id du match à supprimer
     */
    public void deleteMatchPoule(Long id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Match de poule inexistant");
        }
        else {
            repository.deleteById(id);
        }
    }
}
