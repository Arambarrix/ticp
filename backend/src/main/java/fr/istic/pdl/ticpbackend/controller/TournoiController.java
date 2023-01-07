package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.Tournoi;
import fr.istic.pdl.ticpbackend.service.TournoiService;
import fr.istic.pdl.ticpbackend.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Ce controller permet d'utiliser les services du tournoi
 */

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("api/v1/tournoi")
public class TournoiController {
    TournoiService tournoiService;

    @GetMapping("/")
    private ResponseEntity<Object> getTournois(){
        try{
            return new ResponseEntity<>(tournoiService.getTournois(),HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/{id}")
    private ResponseEntity<Object> getTournoi(@PathVariable("id")int id){
        try {
            return new ResponseEntity<>(tournoiService.getTournoi((long)id), HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    private ResponseEntity<Object> createTournoi(@RequestBody Tournoi tournoi){
        try {
            tournoiService.saveTournoi(tournoi);
            return new ResponseEntity<>(tournoi, HttpStatus.OK);

        } catch (RuntimeException e) {
            return Constants.error(e,HttpStatus.FORBIDDEN);
        }
    }
    @PutMapping("/{id}")
    private ResponseEntity<Object> updateTournoi(@PathVariable("id")int id,@RequestBody Tournoi tournoi){
        try{
            tournoiService.updateTournoi(tournoi,(long)id);
            return new ResponseEntity<>(tournoi,HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping("/{idtournoi}/poules")
    private ResponseEntity<Object>getPoules(@PathVariable("idtournoi") int id){
        try{
            return new ResponseEntity<>(tournoiService.getPoules((long)id),HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping("/{idtournoi}/tableaux")
    private ResponseEntity<Object>getTableaux(@PathVariable("idtournoi") int id){
        try{
            return new ResponseEntity<>(tournoiService.getTableaux((long)id),HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping("/{idtournoi}/tableaux/{rang}")
    private ResponseEntity<Object>getTableauxByRang(@PathVariable("idtournoi") int id,@PathVariable("rang") int rang){
        try{
            return new ResponseEntity<>(tournoiService.getTableauxByRang((long)id,(long)rang),HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping("/{idtournoi}/equipes")
    private ResponseEntity<Object>getEquipes(@PathVariable("idtournoi") int id){
        try{
            return new ResponseEntity<>(tournoiService.getEquipes((long)id),HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,HttpStatus.FORBIDDEN);
        }
    }
    @PutMapping("/{id}/create-groupes")
    private ResponseEntity<Object> createGroupes(@PathVariable("id") int id){
        try {
            tournoiService.createGroupes((long)id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,HttpStatus.FORBIDDEN);
        }
    }
    @PutMapping("/{id}/create-tableaux")
    private ResponseEntity<Object> createTableaux(@PathVariable("id")int id){
       try {
           tournoiService.createTableaux((long)id);
           return new ResponseEntity<>(HttpStatus.OK);
       }catch (RuntimeException e){
           return Constants.error(e,HttpStatus.FORBIDDEN);
       }
    }
    @DeleteMapping("/{id}")
    private void deleteTournoi(@PathVariable("id") int id){
        tournoiService.deleteTournoi((long) id);
    }

}