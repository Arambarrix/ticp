package fr.istic.pdl.ticpbackend.service;

import fr.istic.pdl.ticpbackend.model.Tournoi;
import fr.istic.pdl.ticpbackend.repository.TournoiRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TournoiService {
    TournoiRepository tournoiRepository;

    public Tournoi getTournoi(int id){
        return tournoiRepository.findById(new Long(id)).get();
    }

    public void saveTournoi(Tournoi tournoi){
        tournoiRepository.save(tournoi);
    }
}
