package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.Tournoi;
import fr.istic.pdl.ticpbackend.service.TournoiService;
import fr.istic.pdl.ticpbackend.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/")
    private ResponseEntity<Object> getTournois(){
        try{
            return Constants.success(tournoiService.getTournois(),200);
        }catch (RuntimeException e){
            return Constants.error(e,404);
        }

    }
    @GetMapping("/{id}")
    private ResponseEntity<Object> getTournoi(@PathVariable("id")int id){
        try {
            return Constants.success(tournoiService.getTournoi((long)id),200);
        }catch (RuntimeException e){
            return Constants.error(e,404);
        }
    }
    @PostMapping("/")
    private ResponseEntity<Object> createTournoi(@RequestBody Tournoi tournoi){
        try {
            tournoiService.saveTournoi(tournoi);
            return Constants.success(tournoi,200);

        } catch (RuntimeException e) {
            return Constants.error(e,403);
        }
    }
    @PutMapping("/{id}")
    private ResponseEntity<Object> updateTournoi(@PathVariable("id")int id,@RequestBody Tournoi tournoi){
        try{
            tournoiService.updateTournoi(tournoi,(long)id);
            return Constants.success(tournoi,200);
        }catch (RuntimeException e){
            return Constants.error(e,403);
        }
    }
    @GetMapping("/{idtournoi}/poules")
    private ResponseEntity<Object>getPoules(@PathVariable("idtournoi") int id){
        try{
            tournoiService.getTournoi((long)id);
            return Constants.success(tournoiService.getPoules((long)id), 200);
        }catch (RuntimeException e){
            return Constants.error(e,403);
        }
    }
    @GetMapping("/{idtournoi}/tableaux")
    private ResponseEntity<Object>getTableaux(@PathVariable("idtournoi") int id){
        try{
            tournoiService.getTournoi((long)id);
            return Constants.success(tournoiService.getTableaux((long)id), 200);
        }catch (RuntimeException e){
            return Constants.error(e,403);
        }
    }
    @GetMapping("/{idtournoi}/tableaux/{rang}")
    private ResponseEntity<Object>getTableauxByRang(@PathVariable("idtournoi") int id,@PathVariable("rang") int rang){
        try{
            tournoiService.getTournoi((long)id);
            return Constants.success(tournoiService.getTableauxByRang((long)id,(long)rang), 200);
        }catch (RuntimeException e){
            return Constants.error(e,403);
        }
    }
    @GetMapping("/{idtournoi}/equipes")
    private ResponseEntity<Object>getEquipes(@PathVariable("idtournoi") int id){       
        try{
            tournoiService.getTournoi((long)id);
            return Constants.success(tournoiService.getEquipes((long)id), 200);
        }catch (RuntimeException e){
            return Constants.error(e,404);
        }
    }
    @PutMapping("/{id}/create-groupes")
    private ResponseEntity<Object> createGroupes(@PathVariable("id") int id){
        try {
            tournoiService.createGroupes((long)id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,403);
        }
    }
    @PutMapping("/{id}/create-tableaux")
    private ResponseEntity<Object> createTableaux(@PathVariable("id")int id){
       try {
           tournoiService.createTableaux((long)id);
           return new ResponseEntity<>(HttpStatus.OK);
       }catch (RuntimeException e){
           return Constants.error(e,403);
       }
    }
    @DeleteMapping("/{id}")
    private void deleteTournoi(@PathVariable("id") int id){
        tournoiService.deleteTournoi((long) id);
    }
     
}