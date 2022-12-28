package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.Joueur;
import fr.istic.pdl.ticpbackend.service.EquipeService;
import fr.istic.pdl.ticpbackend.service.JoueurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import fr.istic.pdl.ticpbackend.model.Equipe;
import java.util.*;
/**
 * Ce controlleur permet d'utiliser les services des équipes
 */

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("api/v1/equipes")
public class EquipeController {
    EquipeService equipeService;
    // ecrire les methode ici

    @GetMapping("/{id}")
    private Optional<Equipe> getEquipe(@PathVariable("id") int id){
        return equipeService.getEquipe(Long.valueOf(id));
    }

    @PostMapping("/")
    private void saveEquipe(@RequestBody Equipe equipe){
        equipeService.saveEquipe(equipe);
    }
    @PutMapping("/{id}")
    private void updateEquipe(@RequestBody Equipe equipe, @PathVariable("id") int id){

        equipeService.updateEquipe(equipe);
    }

    @DeleteMapping("/{id}")
    private void deleteEquipe(@PathVariable("id") long id){
        equipeService.deleteEquipe(id);
    }

    @GetMapping("/get-joueurs/id")
    private List<Joueur> getJoueurs(@PathVariable("id")int id){
        return equipeService.getEquipe((long)id).get().getJoueurs();
    }
    @GetMapping("/get-joueur/idequipe/idjoueur")
    private Joueur getJoueur(@PathVariable("idequipe")int idequipe, @PathVariable("idjoueur")int idjoueur){
        return equipeService.getJoueur(idequipe,idjoueur);
    }

}
