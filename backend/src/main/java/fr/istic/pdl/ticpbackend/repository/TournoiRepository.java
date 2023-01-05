package fr.istic.pdl.ticpbackend.repository;

import fr.istic.pdl.ticpbackend.model.Tournoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface TournoiRepository extends JpaRepository<Tournoi,Long> {

}
