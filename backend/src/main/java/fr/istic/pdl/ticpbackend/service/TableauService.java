package fr.istic.pdl.ticpbackend.service;

import fr.istic.pdl.ticpbackend.model.Tableau;
import fr.istic.pdl.ticpbackend.repository.TableauRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TableauService {
    TableauRepository repository;
    public Tableau getTableau(int id){
        return repository.findById(new Long(id)).get();
    }
    public void saveTableau(Tableau tableau){
        repository.save(tableau);
    }
    public void deleteTableau(Long id){
        repository.deleteById(id);
    }
}
