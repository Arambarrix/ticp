package fr.istic.pdl.ticpbackend.service;

import fr.istic.pdl.ticpbackend.model.Equipe;
import fr.istic.pdl.ticpbackend.repository.EquipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EquipeService {
    EquipeRepository repository;
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
}
