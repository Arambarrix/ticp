package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.Tournoi;
import fr.istic.pdl.ticpbackend.service.TournoiService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("api/v1/tournoi")
public class TournoiController {
    TournoiService tournoiService;

    @GetMapping("/{id}")
    private Tournoi getTournoi(@PathVariable("id") int id){
        return tournoiService.getTournoi(id);
    }

    @PostMapping
    private void saveTournoi(@RequestBody Tournoi tournoi){
        try {
            tournoiService.saveTournoi(tournoi);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/{id}")
    private void updateTournoi(@RequestBody Tournoi tournoi, @PathVariable("id") int id){
        Tournoi tour = tournoiService.getTournoi(id);
        tour = tournoi;
        try {
            tournoiService.saveTournoi(tour);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @PutMapping("/groupes/{id}")
    private void createGroupes(@RequestBody Tournoi tournoi, @PathVariable("id") int id){
        tournoiService.createGroupes(tournoi);
    }
    @PutMapping("/tableaux/{id}")
    private void createTableaux(@RequestBody Tournoi tournoi, @PathVariable("id") int id){
        tournoiService.createTableaux(tournoi);
    }
    @DeleteMapping
    private void deleteTournoi(){
        tournoiService.deleteTournoi();
    }
}
