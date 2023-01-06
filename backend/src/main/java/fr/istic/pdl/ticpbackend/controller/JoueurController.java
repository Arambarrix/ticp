package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.Equipe;
import fr.istic.pdl.ticpbackend.model.Joueur;
import fr.istic.pdl.ticpbackend.service.JoueurService;
import fr.istic.pdl.ticpbackend.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Ce controller permet d'utiliser les services des joueurs
 */
@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("api/v1/joueurs")
public class JoueurController {
    JoueurService service;

    @GetMapping("/")
    private ResponseEntity<Object> getJoueurs(){
        try{
            return new ResponseEntity<>(service.getJoueurs(), HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,403);
        }
    }
    @GetMapping("/{id}")
    private ResponseEntity<Object> getJoueur(@PathVariable("id")int id){
        try{
            return new ResponseEntity<>(service.getJoueur((long)id).get(),HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,403);
        }
    }

    @PostMapping("/")
    private ResponseEntity<Object> saveJoueur(@RequestBody Joueur joueur){
        try{
            service.saveJoueur(joueur);
            return new ResponseEntity<>(joueur,HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,404);
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity<Object> updateJoueur(@PathVariable("id")int id,@RequestBody Joueur joueur){
        try{
            service.updateJoueur(joueur);
            return new ResponseEntity<>(joueur,HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,404);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Object> deleteJoueur(@PathVariable("id")int id){
        try{
            service.deleteJoueur((long)id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,404);
        }
    }

    @GetMapping("/{id}/equipe")
    private ResponseEntity<Object> getEquipe(@PathVariable("id")int id){
        try{
            return new ResponseEntity<>(service.getEquipe((long)id),HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,403);
        }
    }

}
