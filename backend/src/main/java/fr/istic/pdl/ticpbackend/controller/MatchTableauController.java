package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.MatchTableau;
import fr.istic.pdl.ticpbackend.model.MatchTableau;
import fr.istic.pdl.ticpbackend.service.MatchTableauService;
import fr.istic.pdl.ticpbackend.service.MatchTableauService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.*;
/**
 * Ce controller permet d'utiliser les services des matchs de tableau
 */
@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("api/v1/match_tableaux")
public class MatchTableauController {
    MatchTableauService service;
    @GetMapping("/all")
    private List<MatchTableau> getMatchTableaux(){
        return service.getMatchsTableaux();
    }

    @GetMapping("/{id}")
    private MatchTableau getMatchTableau(@PathVariable("id") int id){
        return service.getMatchTableau((long)id).get();
    }

    @PutMapping("/update/{id}")
    private void updateMatchTableau(@PathVariable("id")int id, @RequestBody MatchTableau matchTableau){
        if(matchTableau.getId()==id){
            service.updateMatchTableau(matchTableau);
        }
    }

    @DeleteMapping("/delete/{id}")
    private void deleteMatchTableau(@PathVariable("id")int id){
        service.deleteMatchTableau((long)id);
    }
}
