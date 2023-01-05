package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.Equipe;
import fr.istic.pdl.ticpbackend.model.Joueur;
import fr.istic.pdl.ticpbackend.service.JoueurService;
import fr.istic.pdl.ticpbackend.strategy.SaveJoueurPhoto;
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
    SaveJoueurPhoto saveJoueurPhoto;

    @GetMapping("/")
    private List<Joueur> getJoueurs(){
        return service.getJoueurs();
    }
    @GetMapping("/{id}")
    private Joueur getJoueur(@PathVariable("id")int id){
        return service.getJoueur((long)id).get();
    }

    @PostMapping("/save")
    private void saveJoueur(@RequestBody Joueur joueur, @RequestAttribute("photo") File photo){
        service.saveJoueur(joueur);
        try {
            InputStream inputStream = new FileInputStream(photo);
            saveJoueurPhoto.savePhoto(joueur.getId(),inputStream,"");
        }catch (Exception e){

        }

    }

    @PutMapping("/update/{id}")
    private void updateJoueur(@PathVariable("id")int id,@RequestBody Joueur joueur, @RequestAttribute(required = false,value = "photo") File photo){
        if(joueur.getId()==id){
            service.updateJoueur(joueur);
            try {
                InputStream inputStream = new FileInputStream(photo);
                saveJoueurPhoto.savePhoto(joueur.getId(),inputStream,"");
            }catch (Exception e){

            }
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
