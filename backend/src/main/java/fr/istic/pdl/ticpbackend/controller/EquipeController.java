package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.*;
import fr.istic.pdl.ticpbackend.service.EquipeService;
import fr.istic.pdl.ticpbackend.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

/**
 * Ce controller permet d'utiliser les services des équipes
 */
@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("api/v1/equipes")
public class EquipeController {
    EquipeService equipeService;

    @GetMapping("/{id}")
    private ResponseEntity<Object> getEquipe(@PathVariable("id") int id){
        try{
            return new ResponseEntity<>(equipeService.getEquipe(Long.valueOf(id)), OK);
        }catch (RuntimeException e){
            return Constants.error(e,403);
        }
    }
    @GetMapping("/")
    private ResponseEntity<Object> getEquipes(@RequestParam(required = false,value = "pageNo")int pageNo,@RequestParam(required = false,value = "pageSize") int pageSize){
        try{
            return new ResponseEntity<>(equipeService.getEquipes((long) pageNo, (long) pageSize), OK);
        }catch (RuntimeException e){
            return Constants.error(e,404);
        }
    }

    @PostMapping("/")
    private ResponseEntity<Object> saveEquipe(@RequestBody Equipe equipe){
        try {
            equipeService.saveEquipe(equipe);
            return Constants.success(equipe, 200);
        }catch (RuntimeException e){
            return Constants.error(e,404);
        }
    }
    @PutMapping("/{id}")
    private ResponseEntity<Object> updateEquipe(@RequestBody Equipe equipe, @PathVariable("id") int id){
        try{
            equipeService.updateEquipe((long)id,equipe);
            return Constants.success(equipeService.getEquipe((long)id), 200);

        }catch (RuntimeException e){
            return Constants.error(e,404);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Object> deleteEquipe(@PathVariable("id") long id){
        try{
            equipeService.deleteEquipe(id);
            return new ResponseEntity<>(OK);
        }catch (RuntimeException e){
            return Constants.error(e,404);
        }
    }

    @GetMapping("/{id}/joueurs/")
    private ResponseEntity<Object> getJoueurs(@PathVariable("id")int id){
        try{
            return Constants.success(equipeService.getJoueurs((long)id), 200);

        }catch (RuntimeException e){
            return Constants.error(e,403);
        }
    }
    @GetMapping("/{idequipe}/joueurs/{idjoueur}")
    private ResponseEntity<Object> getJoueur(@PathVariable("idequipe")int idequipe, @PathVariable("idjoueur")int idjoueur){
        try{
            return new ResponseEntity<>(equipeService.getJoueur((long)idequipe,(long)idjoueur),OK);
        }catch (RuntimeException e){
            return Constants.error(e,403);
        }
    }
    @GetMapping("/{id}/progression")
    private ResponseEntity<Object> getProgression(@PathVariable("id")int id){
        try{
            return Constants.success(equipeService.getMatchsTableau((long)id), 200);

        }catch (RuntimeException e){
            return Constants.error(e,404);
        }
    }

    @GetMapping("/{idequipe}/matchstableau/{idmatch}")
    private ResponseEntity<Object> getMatchTableau(@PathVariable("idequipe")int idequipe,@PathVariable("idmatch")int idmatch){
        try{
            return new ResponseEntity<>(equipeService.getMatchTableau((long)idequipe,(long)idmatch),OK);
        }catch (RuntimeException e){
            return Constants.error(e,403);
        }
    }

    @GetMapping("/{id}/matchspoule")
    private ResponseEntity<Object> getMatchsPoule(@PathVariable("id")int id){
        try{
            return new ResponseEntity<>(equipeService.getMatchsPoules((long)id),OK);
        }catch (RuntimeException e){
            return Constants.error(e,404);
        }
    }

    @GetMapping("/{idequipe}/matchspoule/{idmatch}")
    private ResponseEntity<Object> getMatchPoule(@PathVariable("idequipe")int idequipe,@PathVariable("idmatch")int idmatch){
        try{
            return new ResponseEntity<>(equipeService.getMatchPoule((long)idequipe,(long)idmatch),OK);
        }catch (RuntimeException e){
            return Constants.error(e,403);
        }
    }

    @GetMapping("/{id}/poule")
    private ResponseEntity<Object> getPoule(@PathVariable("id")int id){
        try{
            return new ResponseEntity<>(equipeService.getPoule((long)id),OK);
        }catch (RuntimeException e){
            return Constants.error(e,403);
        }
    }

    @GetMapping("/{id}/tableau")
    private ResponseEntity<Object> getTableau(@PathVariable("id")int id){
        try{
            return new ResponseEntity<>(equipeService.getTableau((long)id),OK);
        }catch (RuntimeException e){
            return Constants.error(e,403);
        }
    }

}
