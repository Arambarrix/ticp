package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.Tournoi;
import fr.istic.pdl.ticpbackend.service.TournoiService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Ce controller permet d'utiliser les services du tournoi
 */

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("api/v1/tournoi")
public class TournoiController {
    TournoiService tournoiService;
    @GetMapping("/{id}")
    private Tournoi getTournoi(@PathVariable("id")int id){
        return tournoiService.getTournoi((long)id);
    }
    @PostMapping("/save")
    private void saveTournoi(@RequestBody Tournoi tournoi){
        try {
            tournoiService.saveTournoi(tournoi);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping("/update/{id}")
    private void updateTournoi(@PathVariable("id")int id,@RequestBody Tournoi tournoi){
        if(tournoi.getId()==tournoiService.getTournoi((long)id).getId()) {
            tournoiService.updateTournoi(tournoi);
        }
    }
    @PutMapping("/create-groupes")
    private void createGroupes(){
        tournoiService.createGroupes();
    }
    @PutMapping("/create-tableaux")
    private void createTableaux(){
        tournoiService.createTableaux();
    }
    @DeleteMapping("/delete")
    private void deleteTournoi(){
        tournoiService.deleteTournoi();
    }
}