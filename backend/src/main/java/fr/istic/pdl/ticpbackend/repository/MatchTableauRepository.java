package fr.istic.pdl.ticpbackend.repository;

import fr.istic.pdl.ticpbackend.model.Equipe;
import fr.istic.pdl.ticpbackend.model.MatchPoule;
import fr.istic.pdl.ticpbackend.model.MatchTableau;
import fr.istic.pdl.ticpbackend.model.Tournoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchTableauRepository extends JpaRepository<MatchTableau,Long> {
    @Query("SELECT DISTINCT t FROM MatchTableau m, Tournoi t WHERE (m.equipeA.tournoi.id=t.id or m.equipeB.tournoi.id=t.id) and m.id=?1")
    Tournoi getTournoi(Long id);
    @Query("SELECT DISTINCT tour FROM MatchTableau")
    List<Integer> findTours();
    @Query("SELECT m FROM MatchTableau m WHERE m.tour =?1")
    List<MatchTableau> findMatchsByTours(int tour);

    @Query("SELECT DISTINCT e FROM Equipe e, MatchTableau m WHERE (e.id=m.equipeA.id or e.id=m.equipeB.id) and e.id =?1 and m.tour= ?2")
    Equipe findEquipeByMatchInTour(long equipe,int tour);
}
