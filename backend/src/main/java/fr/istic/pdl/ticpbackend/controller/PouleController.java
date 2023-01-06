package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.Equipe;
import fr.istic.pdl.ticpbackend.model.MatchPoule;
import fr.istic.pdl.ticpbackend.model.Poule;
import fr.istic.pdl.ticpbackend.service.PouleService;
import fr.istic.pdl.ticpbackend.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Ce controller permet d'utiliser les services des poules
 */
@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("api/v1/groupes")
public class PouleController {
    PouleService pouleService;

    @GetMapping("/{id}/equipes")
    private ResponseEntity<Object> getRanking(@PathVariable("id") int id){
        try{
            return new ResponseEntity<>(pouleService.getRanking((long)id),HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    @GetMapping("/{id}/classement")
    private ResponseEntity<Object> getClassement(@PathVariable("id")int id){
        try{
            return new ResponseEntity<>(pouleService.getClassement((long)id), HttpStatus.ACCEPTED);
        }catch (RuntimeException e){
            return Constants.error(e,HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<Object> getPoule(@PathVariable("id")int id){
        try{
            return new ResponseEntity<>(pouleService.getPoule((long)id).get(),HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/")
    private ResponseEntity<Object> getPoules(){
        try{
            return new ResponseEntity<>(pouleService.getPoules(),HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity<Object> updatePoule(@PathVariable("id")int id,@RequestBody Poule poule){
        try{
            pouleService.savePoule(poule);
            return new ResponseEntity<>(poule,HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    @GetMapping("/{id}/matchs")
    private ResponseEntity<Object> getMatchs(@PathVariable("id")int id){
        try{
            return new ResponseEntity<>(pouleService.getMatchsPoules((long)id),HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<Object> deletePoule(@PathVariable("id")int id){
        try{
            pouleService.deletePoule((long)id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,HttpStatus.FORBIDDEN);
        }
    }
}
