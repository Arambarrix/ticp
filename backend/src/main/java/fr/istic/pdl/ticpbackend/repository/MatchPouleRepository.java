package fr.istic.pdl.ticpbackend.repository;

import fr.istic.pdl.ticpbackend.model.MatchPoule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchPouleRepository extends JpaRepository<MatchPoule,Long> {
}
