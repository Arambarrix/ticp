package fr.istic.pdl.ticpbackend.service;

import fr.istic.pdl.ticpbackend.model.Equipe;
import fr.istic.pdl.ticpbackend.repository.EquipeRepository;
import fr.istic.pdl.ticpbackend.repository.JoueurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public void deleteJoueur(Long equipe,Long joueur){
        if(repository.findById(equipe).get().getJoueurs().contains(joueurRepository.getReferenceById(joueur))){
            repository.findById(equipe).get().getJoueurs().remove(joueurRepository.getReferenceById(joueur));
        }
        else {
            throw new RuntimeException("Joueur introuvable");
        }
    }

}
