package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.Equipe;
import fr.istic.pdl.ticpbackend.model.MatchPoule;
import fr.istic.pdl.ticpbackend.model.MatchTableau;
import fr.istic.pdl.ticpbackend.model.Poule;
import fr.istic.pdl.ticpbackend.service.PouleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Ce controlleur permet d'utiliser les services des poules
 */
@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("api/v1/groupes")
public class PouleController {
    PouleService pouleService;


    @GetMapping("/ranking/{id}")
    private List<Equipe> getRanking(@PathVariable("id") int id){
        return pouleService.getRanking((long)id);
    }

    @GetMapping("/{id}")
    private Poule getPoule(@PathVariable("id")int id){
        return pouleService.getPoule((long)id).get();
    }
    @GetMapping("/all")
    private List<Poule> getPoules(){
        return pouleService.getPoules();
    }

    @PutMapping("/update/{id}")
    private void updatePoule(@PathVariable("id")int id,@RequestBody Poule poule){
        if(poule.getId()==id){
            pouleService.savePoule(poule);
        }

    }
    @GetMapping("/get-matchs/{id}")
    private List<MatchPoule> getMatchs(@PathVariable("id")int id){
        return pouleService.getMatchsPoules((long)id);
    }
    @DeleteMapping("/delete/{id}")
    private void deletePoule(@PathVariable("id")int id){
        pouleService.deletePoule((long)id);
    }
}
