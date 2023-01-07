package fr.istic.pdl.ticpbackend.repository;

import fr.istic.pdl.ticpbackend.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo,Long> {
}
