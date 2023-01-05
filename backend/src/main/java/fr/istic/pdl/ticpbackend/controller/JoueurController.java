package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.Equipe;
import fr.istic.pdl.ticpbackend.model.Joueur;
import fr.istic.pdl.ticpbackend.service.JoueurService;
import lombok.AllArgsConstructor;
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
    private List<Joueur> getJoueurs(){
        return service.getJoueurs();
    }
    @GetMapping("/{id}")
    private Joueur getJoueur(@PathVariable("id")int id){
        return service.getJoueur((long)id).get();
    }

    @PostMapping("/save")
    private void saveJoueur(@RequestBody Joueur joueur){
        service.saveJoueur(joueur);
    }

    @PutMapping("/update/{id}")
    private void updateJoueur(@PathVariable("id")int id,@RequestBody Joueur joueur){
        if(joueur.getId()==id){
            service.updateJoueur(joueur);
        }
    }

    @DeleteMapping("/delete/{id}")
    private void deleteJoueur(@PathVariable("id")int id){
        service.deleteJoueur((long)id);
    }

    @GetMapping("/get-equipe/{id}")
    private Equipe getEquipe(@PathVariable("id")int id){
        return service.getEquipe((long)id);
    }

}
