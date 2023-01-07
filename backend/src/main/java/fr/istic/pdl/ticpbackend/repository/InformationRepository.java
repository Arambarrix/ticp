package fr.istic.pdl.ticpbackend.repository;

import fr.istic.pdl.ticpbackend.model.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepository extends JpaRepository<Information,Long> {
}
