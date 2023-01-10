package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.MatchTableau;
import fr.istic.pdl.ticpbackend.service.MatchTableauService;
import fr.istic.pdl.ticpbackend.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Ce controller permet d'utiliser les services des matchs de tableau
 */
@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("api/v1/match_tableaux")
public class MatchTableauController {
    MatchTableauService service;
    @GetMapping("/")
    private ResponseEntity<Object> getMatchTableaux(){
        try{
            return new ResponseEntity<>(service.getMatchsTableaux(), HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,404);
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<Object> getMatchTableau(@PathVariable("id") int id){
        try{
            return new ResponseEntity<>(service.getMatchTableau((long)id),HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,404);
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity<Object> updateMatchTableau(@PathVariable("id")int id, @RequestBody MatchTableau matchTableau){
        //try {
            service.updateMatchTableau((long)id,matchTableau);
            return new ResponseEntity<>(matchTableau,HttpStatus.OK);
        /*}catch (RuntimeException e){
            return Constants.error(e,404);
        }

         */
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Object> deleteMatchTableau(@PathVariable("id")int id){
        try {
            service.deleteMatchTableau((long)id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,404);
        }
    }
}
