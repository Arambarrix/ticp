package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.Tournoi;
import fr.istic.pdl.ticpbackend.service.TournoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TournoiController {
    @Autowired
    TournoiService tournoiService;

    @GetMapping("/tournoi/{id}")
    private Tournoi getTournoi(@PathVariable("id") int id){
        return tournoiService.getTournoi(id);
    }

    @PostMapping("/tournoi")
    private void saveTournoi(@RequestBody Tournoi tournoi){
        tournoiService.saveTournoi(tournoi);
    }
}
