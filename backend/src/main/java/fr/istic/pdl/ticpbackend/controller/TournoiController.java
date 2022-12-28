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

    @GetMapping("/")
    private Tournoi getTournoi(){
        return tournoiService.getTournoi();
    }

    @PostMapping("/save")
    private void saveTournoi(@RequestBody Tournoi tournoi){
        try {
            tournoiService.saveTournoi(tournoi);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/update")
    private void updateTournoi(@RequestBody Tournoi tournoi){
        try {
            if(tournoi.getId()==tournoiService.getTournoi().getId()){
                tournoiService.updateTournoi(tournoi);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
