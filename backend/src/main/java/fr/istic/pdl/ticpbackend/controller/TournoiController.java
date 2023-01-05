package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.Tournoi;
import fr.istic.pdl.ticpbackend.service.TournoiService;
import fr.istic.pdl.ticpbackend.strategy.SaveTournoiPhoto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Ce controller permet d'utiliser les services du tournoi
 */

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("api/v1/tournoi")
public class TournoiController {
    TournoiService tournoiService;
    SaveTournoiPhoto tournoiPhoto;

    @GetMapping("/")
    private Tournoi getTournoi(){
        return tournoiService.getTournoi();
    }

    @PostMapping("/save")
    private void saveTournoi(@RequestBody Tournoi tournoi, @RequestAttribute(name = "photo",required = false) File photo, @RequestAttribute(name = "description",required = false) String description){
        try {
            tournoiService.saveTournoi(tournoi);
            try {
                InputStream inputStream = new FileInputStream(photo);
                tournoiPhoto.saveLogo(tournoi.getId(),inputStream,"");
            }catch (Exception e){

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/update")
    private void updateTournoi(@RequestBody Tournoi tournoi, @RequestAttribute(name ="photo", required = false) File photo){
        try {
            if(tournoi.getId()==tournoiService.getTournoi().getId()){
                tournoiService.updateTournoi(tournoi);
                try {
                    InputStream inputStream = new FileInputStream(photo);
                    tournoiPhoto.saveLogo(tournoi.getId(),inputStream,"");
                }catch (Exception e){

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @PutMapping("/add-photo")
    private void addPhoto(@RequestBody Tournoi tournoi, @RequestAttribute(name = "photo",required = false) File photo){
        try {
            if(tournoi.getId()==tournoiService.getTournoi().getId()){
                try {
                    InputStream inputStream = new FileInputStream(photo);
                    tournoiPhoto.savePhoto(tournoi.getId(),inputStream,"");
                }catch (Exception e){

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @PutMapping("/create-groupes")
    private void createGroupes(){
        tournoiService.createGroupes();
    }
    @PutMapping("/create-tableaux")
    private void createTableaux(){
        tournoiService.createTableaux();
    }
    @DeleteMapping("/delete")
    private void deleteTournoi(){
        tournoiService.deleteTournoi();
    }
}
