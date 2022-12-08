package fr.istic.pdl.ticpbackend.service;

import fr.istic.pdl.ticpbackend.model.Equipe;
import fr.istic.pdl.ticpbackend.model.Poule;
import fr.istic.pdl.ticpbackend.repository.EquipeRepository;
import fr.istic.pdl.ticpbackend.repository.PouleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.*;


@Service
@AllArgsConstructor
public class PouleService {
    PouleRepository repository;
    public Optional<Poule> getPoule(Long id){
        return repository.findById(id);
    }
    public void savePoule(Poule poule){
        repository.save(poule);
    }
    public void deletePoule(Long id){
        repository.deleteById(id);
    }

    public List<Equipe> getAllTeams(Long id){
        return repository.findAllTeamsByPouleQuery(id);
    }
}
