package fr.istic.pdl.ticpbackend.service;

import fr.istic.pdl.ticpbackend.model.Equipe;
import fr.istic.pdl.ticpbackend.model.MatchPoule;
import fr.istic.pdl.ticpbackend.repository.MatchPouleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MatchPouleService {
    MatchPouleRepository repository;
    public Optional<MatchPoule> getMatchPoule(Long id){
        return repository.findById(id);
    }
    public void updateMatchPoule(MatchPoule match){
        repository.save(match);
    }
    public void deleteMatchPoule(Long id){
        repository.deleteById(id);
    }
}
