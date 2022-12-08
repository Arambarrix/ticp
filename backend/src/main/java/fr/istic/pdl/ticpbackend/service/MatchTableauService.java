package fr.istic.pdl.ticpbackend.service;

import fr.istic.pdl.ticpbackend.model.MatchTableau;
import fr.istic.pdl.ticpbackend.repository.MatchTableauRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MatchTableauService {
    MatchTableauRepository repository;
    public Optional<MatchTableau> getMatchTableau(Long id){
        return repository.findById(id);
    }
    public void saveMatchTableau(MatchTableau match){
        repository.save(match);
    }
    public void deleteMatchTableau(Long id){
        repository.deleteById(id);
    }
}
