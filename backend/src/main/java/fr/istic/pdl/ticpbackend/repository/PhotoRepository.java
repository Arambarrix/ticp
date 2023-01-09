package fr.istic.pdl.ticpbackend.repository;

import fr.istic.pdl.ticpbackend.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,Long> {
}
