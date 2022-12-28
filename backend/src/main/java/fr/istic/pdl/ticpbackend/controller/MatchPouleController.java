package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.MatchPoule;
import fr.istic.pdl.ticpbackend.service.MatchPouleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.*;
/**
 * Ce controlleur permet d'utiliser les services des matchs de poule
 */
@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("api/v1/match_poules")
public class MatchPouleController {
    MatchPouleService matchPouleService;
    @GetMapping("/all")
    private List<MatchPoule> getMatchsPoules(){
        return matchPouleService.getMatchsPoules();
    }

    @GetMapping("/{id}")
    private MatchPoule getMatchPoule(@PathVariable("id") int id){
        return matchPouleService.getMatchPoule((long)id).get();
    }

    @PutMapping("/update/{id}")
    private void updateMatchPoule(@PathVariable("id")int id, @RequestBody MatchPoule matchPoule){
        if(matchPoule.getId()==id){
            matchPouleService.updateMatchPoule(matchPoule);
        }
    }

    @DeleteMapping("/delete/{id}")
    private void deleteMatchPoule(@PathVariable("id")int id){
        matchPouleService.deleteMatchPoule((long)id);
    }
}
