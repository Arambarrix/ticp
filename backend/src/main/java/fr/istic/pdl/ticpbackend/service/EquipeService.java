package fr.istic.pdl.ticpbackend.service;

import fr.istic.pdl.ticpbackend.model.Equipe;
import fr.istic.pdl.ticpbackend.model.Joueur;
import fr.istic.pdl.ticpbackend.repository.EquipeRepository;
import fr.istic.pdl.ticpbackend.repository.JoueurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Ce service définit les fonctions propres aux équipes
 */
@Service
@AllArgsConstructor
public class EquipeService {
    EquipeRepository repository;
    JoueurRepository joueurRepository;
    public Optional<Equipe> getEquipe(Long id){
        return repository.findById(id);
    }
    public void saveEquipe(Equipe equipe){
        repository.save(equipe);
    }
    public void deleteEquipe(Long id){
        repository.deleteById(id);
    }
    public void updateEquipe(Equipe equipe){
        if(repository.existsById(equipe.getId()))
        {
            repository.save(equipe);
        }
    }

    /**
     * Permet de récupérer un joueur de l'équipe
     * @param idEquipe identifiant de l'équipe
     * @param idJoueur identifiant du joueur
     * @return le joueur cherhcé dans l'équipe
     * @throws RuntimeException si le joueur est inexistant dans l'équipe
     */
    public Joueur getJoueur(int idEquipe, int idJoueur){
        Joueur joueur=joueurRepository.findById((long)idJoueur).get();
        if(joueurRepository.findById((long)idJoueur).get().getEquipe().getId()!=idEquipe){
            throw new RuntimeException("Joueur inexistant dans l'équipe");
        }
        else{
            return joueur;
        }
    }
}
