package fr.istic.pdl.ticpbackend.repository;

import fr.istic.pdl.ticpbackend.model.MatchTableau;
import fr.istic.pdl.ticpbackend.model.Tableau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableauRepository extends JpaRepository<Tableau,Long> {
    @Query("SELECT m FROM MatchTableau  m  WHERE m.tableau.id= ?1")
    List<MatchTableau> findMatchsTableaux(Long id);
}
