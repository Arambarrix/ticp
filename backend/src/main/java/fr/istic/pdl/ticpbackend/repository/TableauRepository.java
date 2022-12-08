package fr.istic.pdl.ticpbackend.repository;

import fr.istic.pdl.ticpbackend.model.Tableau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableauRepository extends JpaRepository<Tableau,Long> {
}
