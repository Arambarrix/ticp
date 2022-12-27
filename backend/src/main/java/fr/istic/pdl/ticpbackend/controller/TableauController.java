package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.Tableau;
import fr.istic.pdl.ticpbackend.service.TableauService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("api/v1/tableaux")
public class TableauController {
    TableauService tableauService;
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
    @PutMapping("/next-round/{id}")
    private void nextRound(@PathVariable("id")int id){
        tableauService.nextRound((long)id);
    }
}
