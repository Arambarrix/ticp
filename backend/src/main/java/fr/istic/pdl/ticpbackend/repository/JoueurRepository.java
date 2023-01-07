package fr.istic.pdl.ticpbackend.repository;

import fr.istic.pdl.ticpbackend.model.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoueurRepository extends JpaRepository<Joueur,Long> {
}
