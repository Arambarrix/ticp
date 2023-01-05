package fr.istic.pdl.ticpbackend.repository;

import fr.istic.pdl.ticpbackend.model.MatchPoule;
import fr.istic.pdl.ticpbackend.model.Tournoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchPouleRepository extends JpaRepository<MatchPoule,Long> {
    @Query("SELECT DISTINCT t FROM MatchPoule m, Tournoi t WHERE (m.equipeA.tournoi.id=t.id or m.equipeB.tournoi.id=t.id) and m.id=?1")
    Tournoi getTournoi(Long id);
}
