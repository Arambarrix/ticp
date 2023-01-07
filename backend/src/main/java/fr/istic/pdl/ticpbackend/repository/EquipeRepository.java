package fr.istic.pdl.ticpbackend.repository;

import fr.istic.pdl.ticpbackend.model.Equipe;
import fr.istic.pdl.ticpbackend.model.Poule;
import fr.istic.pdl.ticpbackend.model.Tableau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe,Long> {

    //automatique
    @Query("SELECT distinct p FROM Equipe e, Poule p, MatchPoule  m  WHERE (e.id=m.equipeA.id or e.id=m.equipeB.id) AND p.id = m.poule.id and e.id= ?1")    //This is using a named query method
    Poule findPoule(Long id);

    @Query("SELECT distinct t FROM Equipe e, Tableau t, MatchTableau  m  WHERE (e.id=m.equipeA.id or e.id=m.equipeB.id) AND t.id = m.tableau.id and e.id= ?1")    //This is using a named query method
    Tableau findTableau(Long id);

    List<Equipe> findByNom(String nom);

}
