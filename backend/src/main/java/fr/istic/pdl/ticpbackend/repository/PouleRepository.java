package fr.istic.pdl.ticpbackend.repository;

import fr.istic.pdl.ticpbackend.model.Equipe;
import fr.istic.pdl.ticpbackend.model.Poule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PouleRepository extends JpaRepository<Poule,Long> {
    @Query("SELECT distinct e FROM Equipe e, Poule p, MatchPoule  m  WHERE (e.id=m.equipeA.id or e.id=m.equipeB.id) AND p.id = m.poule.id and m.poule.id= ?1")    //This is using a named query method
    public List<Equipe> findAllTeamsByPouleQuery(Long id);
}
