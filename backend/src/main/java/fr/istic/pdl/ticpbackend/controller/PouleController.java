package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.Equipe;
import fr.istic.pdl.ticpbackend.model.Poule;
import fr.istic.pdl.ticpbackend.service.PouleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("api/v1/poule")
public class PouleController {
    PouleService pouleService;

    @GetMapping("/equipes/{id}")
    private List<Equipe> getAllTeams(@PathVariable("id") int id){
        return pouleService.getAllTeams((long)id);
    }

    @GetMapping("/{id}")
    private Poule getPoule(@PathVariable("id")int id){
        return pouleService.getPoule((long)id).get();
    }

    @PutMapping("/{id}")
    private void updatePoule(@PathVariable("id")int id,@RequestBody Poule poule){
        Poule update = pouleService.getPoule((long)id).get();
        update=poule;
        pouleService.savePoule(update);
    }

    @DeleteMapping("/{id}")
    private void deletePoule(@PathVariable("id")int id){
        pouleService.deletePoule((long)id);
    }
}
