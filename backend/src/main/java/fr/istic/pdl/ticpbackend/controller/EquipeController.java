package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.*;
import fr.istic.pdl.ticpbackend.service.EquipeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.util.*;

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
    private Optional<Equipe> getEquipe(@PathVariable("id") int id){
        return equipeService.getEquipe(Long.valueOf(id));
    }
    @GetMapping("/")
    private List<Equipe> getEquipes(@RequestParam(required = false,value = "pageNo")int pageNo,@RequestParam(required = false,value = "pageSize") int pageSize){
        return equipeService.getEquipes((long) pageNo, (long) pageSize);
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

    @GetMapping("/get-joueurs/{id}")
    private List<Joueur> getJoueurs(@PathVariable("id")int id){
        return equipeService.getJoueurs((long)id);
    }
    @GetMapping("/get-joueur/{idequipe}/{idjoueur}")
    private Joueur getJoueur(@PathVariable("idequipe")int idequipe, @PathVariable("idjoueur")int idjoueur){
        return equipeService.getJoueur((long)idequipe,(long)idjoueur);
    }
    @GetMapping("/progression/{id}")
    private List<MatchTableau> getProgression(@PathVariable("id")int id){
        return equipeService.getMatchsTableau((long)id);
    }

    @GetMapping("/get-match-tableau/{idequipe}/{idmatch}")
    private MatchTableau getMatchTableau(@PathVariable("idequipe")int idequipe,@PathVariable("idmatch")int idmatch){
        return equipeService.getMatchTableau((long)idequipe,(long)idmatch);
    }

    @GetMapping("/get-matchs-poule/{id}")
    private List<MatchPoule> getMatchsPoule(@PathVariable("id")int id){
        return equipeService.getMatchsPoules((long)id);
    }

    @GetMapping("/get-match-poule/{idequipe}/{idmatch}")
    private MatchPoule getMatchPoule(@PathVariable("idequipe")int idequipe,@PathVariable("idmatch")int idmatch){
        return equipeService.getMatchPoule((long)idequipe,(long)idmatch);
    }

    @GetMapping("/get-poule/{id}")
    private Poule getPoule(@PathVariable("id")int id){
        return equipeService.getPoule((long)id);
    }

    @GetMapping("/get-tableau/{id}")
    private Tableau getTableau(@PathVariable("id")int id){
        return equipeService.getTableau((long)id);
    }

}
