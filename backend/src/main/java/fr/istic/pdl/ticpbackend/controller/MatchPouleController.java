package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.MatchPoule;
import fr.istic.pdl.ticpbackend.service.MatchPouleService;
import fr.istic.pdl.ticpbackend.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
/**
 * Ce controller permet d'utiliser les services des matchs de poule
 */
@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("api/v1/match_poules")
public class MatchPouleController {
    MatchPouleService matchPouleService;
    @GetMapping("/")
    private ResponseEntity<Object> getMatchsPoules(){
        try{
            return new ResponseEntity<>(matchPouleService.getMatchsPoules(), HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,404);
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<Object> getMatchPoule(@PathVariable("id") int id){
        try{
            return new ResponseEntity<>(matchPouleService.getMatchPoule((long)id),HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,404);
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity<Object> updateMatchPoule(@PathVariable("id")int id, @RequestBody MatchPoule matchPoule){
        try{
            matchPouleService.updateMatchPoule((long)id,matchPoule);
            return Constants.success(matchPoule,200);

        }catch (RuntimeException e){
            return Constants.error(e,404);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Object> deleteMatchPoule(@PathVariable("id")int id){
        try{
            matchPouleService.deleteMatchPoule((long)id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,404);
        }
    }
}
