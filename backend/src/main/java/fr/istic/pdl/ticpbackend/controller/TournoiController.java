package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.Poule;
import fr.istic.pdl.ticpbackend.model.Tournoi;
import fr.istic.pdl.ticpbackend.service.TournoiService;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    private ResponseEntity<Object> saveTournoi(@RequestBody Tournoi tournoi){
        try {
            tournoiService.saveTournoi(tournoi);
            return new ResponseEntity<>(tournoi, HttpStatus.OK);

        } catch (Exception e) {
            JSONObject obj = new JSONObject();
            obj.put("message",e.getMessage());
            return new ResponseEntity<>(obj ,HttpStatus.FORBIDDEN);
        }
    }
    @PutMapping("/update/{id}")
    private ResponseEntity<Object> updateTournoi(@PathVariable("id")int id,@RequestBody Tournoi tournoi){
        if(tournoi.getId()==tournoiService.getTournoi((long)id).getId()) {
            try {
                tournoiService.updateTournoi(tournoi);
                return new ResponseEntity<>(tournoi,HttpStatus.OK);

            } catch (Exception e) {
                JSONObject obj = new JSONObject();
                obj.put("message",e.getMessage());
                return new ResponseEntity<>(obj ,HttpStatus.FORBIDDEN);
            }
        }
        else {
            JSONObject obj = new JSONObject();
            obj.put("message","Tournoi inexistant");
            return new ResponseEntity<>(obj ,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{idtournoi}/poules")
    private List<Poule> getPoules(@PathVariable("idtournoi") int id){
        return null;
    }
    @PutMapping("/create-groupes")
    private void createGroupes(){
        tournoiService.createGroupes();
    }
    @PutMapping("/create-tableaux")
    private void createTableaux(){
        tournoiService.createTableaux();
    }
    @DeleteMapping("/{id}")
    private void deleteTournoi(@PathVariable("id") int id){
        tournoiService.deleteTournoi((long) id);
    }
}