package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.MatchTableau;
import fr.istic.pdl.ticpbackend.model.Tableau;
import fr.istic.pdl.ticpbackend.service.TableauService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Ce controlleur permet d'utiliser les services des tableaux
 */
@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("api/v1/tableaux")
public class TableauController {
    TableauService tableauService;
    @GetMapping("/{id}")
    private Tableau getTableau(@PathVariable("id")int id){
        return tableauService.getTableau((long)id);
    }
    @GetMapping("/")
    private List<Tableau> getTableaux(){
        return tableauService.getTableaux();
    }
    @DeleteMapping("/{id}")
    private void deleteTableau(@PathVariable("id")int id){
        tableauService.deleteTableau((long)id);
    }
    @PutMapping("/update/{id}")
    private void updateTableau(@PathVariable("id")int id, @RequestBody Tableau tableau){
        if(tableau.getId()==id){
            tableauService.updateTableau(tableau);
        }
    }
    @GetMapping("/get-matchs/{id}")
    private List<MatchTableau> getMatchs(@PathVariable("id")int id){
        return tableauService.getMatchsTableau((long)id);
    }
    @PutMapping("/next-round/{id}")
    private void nextRound(@PathVariable("id")int id){
        tableauService.nextRound((long)id);
    }
}
