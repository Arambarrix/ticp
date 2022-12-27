package fr.istic.pdl.ticpbackend.repository;

import fr.istic.pdl.ticpbackend.model.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe,Long> {

    //automatique

}
