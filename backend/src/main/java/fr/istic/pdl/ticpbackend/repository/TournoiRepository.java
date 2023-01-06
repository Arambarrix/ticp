package fr.istic.pdl.ticpbackend.repository;

import fr.istic.pdl.ticpbackend.model.Poule;
import fr.istic.pdl.ticpbackend.model.Tableau;
import fr.istic.pdl.ticpbackend.model.Tournoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TournoiRepository extends JpaRepository<Tournoi,Long> {
    @Query("SELECT COUNT(t) FROM Tournoi t WHERE YEAR(t.dateDebutTournoi)=?1")
    int getTournoiByYear(Long year);
    @Query("SELECT tab FROM Tournoi t, Tableau tab WHERE t.id =tab.tournoi.id and t.id =?1 and tab.rang=?2")
    List<Tableau> getTableauxByRang(Long tournoi, Long rang);
}
