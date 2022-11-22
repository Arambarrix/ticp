package fr.istic.pdl.ticpbackend.repository;

import fr.istic.pdl.ticpbackend.model.Tournoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournoiRepository extends JpaRepository<Tournoi,Long> {
}
