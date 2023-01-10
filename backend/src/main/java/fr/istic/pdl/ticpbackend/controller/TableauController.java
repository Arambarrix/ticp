package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.MatchTableau;
import fr.istic.pdl.ticpbackend.model.Tableau;
import fr.istic.pdl.ticpbackend.service.TableauService;
import fr.istic.pdl.ticpbackend.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

/**
 * Ce controller permet d'utiliser les services des tableaux
 */
@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("api/v1/tableaux")
public class TableauController {
    TableauService tableauService;
    @GetMapping("/{id}")
    private ResponseEntity<Object> getTableau(@PathVariable("id")int id){
        try{
            return new ResponseEntity<>(tableauService.getTableau((long)id), OK);
        }catch (RuntimeException e){
            return Constants.error(e,404);
        }
    }
    @GetMapping("/")
    private ResponseEntity<Object> getTableaux(){
        try{
            return new ResponseEntity<>(tableauService.getTableaux(), OK);
        }catch (RuntimeException e){
            return Constants.error(e,404);
        }
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<Object> deleteTableau(@PathVariable("id")int id){
        try{
            tableauService.deleteTableau((long)id);
            return new ResponseEntity<>(OK);
        }catch (RuntimeException e){
            return Constants.error(e,403);
        }
    }
    @PutMapping("/{id}")
    private ResponseEntity<Object> updateTableau(@PathVariable("id")int id, @RequestBody Tableau tableau){
        try{
            tableauService.updateTableau(tableau);
            return new ResponseEntity<>(OK);
        }catch (RuntimeException e){
            return Constants.error(e,404);
        }
    }
    @GetMapping("/{id}/matchs")
    private ResponseEntity<Object> getMatchs(@PathVariable("id")int id){
        try{
            return new ResponseEntity<>(tableauService.getMatchsTableau((long)id), OK);
        }catch (RuntimeException e){
            return Constants.error(e,404);
        }
    }
    @PutMapping("/{id}/next-round")
    private ResponseEntity<Object> nextRound(@PathVariable("id")int id) {
        try {
            tableauService.nextRound((long) id);
            return new ResponseEntity<>("Mise à jour de la progression des équipes", OK);
        } catch (RuntimeException e) {
            return Constants.error(e, 404);
        }
    }
    @GetMapping("/{id}/matchs_tableaux_tour")
    private ResponseEntity<Object> getMatchsTableauByTour(@PathVariable("id") int id){
        return new ResponseEntity<>(tableauService.getMatchsByTour(tableauService.getTableau((long)id)),OK);
    }
}
