package fr.istic.pdl.ticpbackend.service;

import fr.istic.pdl.ticpbackend.model.Joueur;
import fr.istic.pdl.ticpbackend.repository.JoueurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class JoueurService {
    JoueurRepository repository;
    public Optional<Joueur> getJoueur(Long id){
        return repository.findById(id);
    }
    public void saveJoueur(Joueur joueur){
        repository.save(joueur);
    }
    public void deleteJoueur(Long id){
        repository.deleteById(id);
    }
}
