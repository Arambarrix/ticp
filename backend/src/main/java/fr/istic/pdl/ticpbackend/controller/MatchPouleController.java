package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.MatchPoule;
import fr.istic.pdl.ticpbackend.service.MatchPouleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("api/v1/match_poule")
public class MatchPouleController {
    MatchPouleService matchPouleService;

    @GetMapping("/{id}")
    private MatchPoule getMatchPoule(@PathVariable("id") int id){
        return matchPouleService.getMatchPoule((long)id).get();
    }

    @PutMapping("/{id}")
    private void updateMatchPoule(@PathVariable("id")int id, @RequestBody MatchPoule matchPoule){
        MatchPoule update = matchPouleService.getMatchPoule((long)id).get();
        update=matchPoule;
        matchPouleService.updateMatchPoule(update);
    }

    @DeleteMapping("/{id}")
    private void deleteMatchPoule(@PathVariable("id")int id){
        matchPouleService.deleteMatchPoule((long)id);
    }
}
