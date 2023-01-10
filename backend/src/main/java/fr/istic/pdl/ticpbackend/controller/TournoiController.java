package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.Information;
import fr.istic.pdl.ticpbackend.model.Photo;
import fr.istic.pdl.ticpbackend.model.Tournoi;
import fr.istic.pdl.ticpbackend.service.TournoiService;
import fr.istic.pdl.ticpbackend.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Ce controller permet d'utiliser les services du tournoi
 */

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("api/v1/tournoi")
public class TournoiController {
    TournoiService tournoiService;

    @GetMapping("/")
    private ResponseEntity<Object> getTournois(){
        try{
            return Constants.success(tournoiService.getTournois(),200);
        }catch (RuntimeException e){
            return Constants.error(e,404);
        }

    }
    @GetMapping("/{id}")
    private ResponseEntity<Object> getTournoi(@PathVariable("id")int id){
        try {
            return Constants.success(tournoiService.getTournoi((long)id),200);
        }catch (RuntimeException e){
            return Constants.error(e,404);
        }
    }
    @PostMapping("/")
    private ResponseEntity<Object> createTournoi(@RequestBody Tournoi tournoi){
        try {
            tournoiService.saveTournoi(tournoi);
            return Constants.success(tournoi,200);

        } catch (RuntimeException e) {
            return Constants.error(e,403);
        }
    }
    @PutMapping("/{id}")
    private ResponseEntity<Object> updateTournoi(@PathVariable("id")int id,@RequestBody Tournoi tournoi){
        try{
            tournoiService.updateTournoi(tournoi,(long)id);
            return Constants.success(tournoi,200);
        }catch (RuntimeException e){
            return Constants.error(e,403);
        }
    }
    @GetMapping("/{idtournoi}/poules")
    private ResponseEntity<Object>getPoules(@PathVariable("idtournoi") int id){
        try{
            tournoiService.getTournoi((long)id);
            return Constants.success(tournoiService.getPoules((long)id), 200);
        }catch (RuntimeException e){
            return Constants.error(e,403);
        }
    }
    @GetMapping("/{idtournoi}/tableaux")
    private ResponseEntity<Object>getTableaux(@PathVariable("idtournoi") int id){
        try{
            tournoiService.getTournoi((long)id);
            return Constants.success(tournoiService.getTableaux((long)id), 200);
        }catch (RuntimeException e){
            return Constants.error(e,403);
        }
    }
    @GetMapping("/{idtournoi}/tableaux/{rang}")
    private ResponseEntity<Object>getTableauxByRang(@PathVariable("idtournoi") int id,@PathVariable("rang") int rang){
        try{
            tournoiService.getTournoi((long)id);
            return Constants.success(tournoiService.getTableauxByRang((long)id,(long)rang), 200);
        }catch (RuntimeException e){
            return Constants.error(e,403);
        }
    }
    @GetMapping("/{idtournoi}/equipes")
    private ResponseEntity<Object>getEquipes(@PathVariable("idtournoi") int id){       
        try{
            tournoiService.getTournoi((long)id);
            return Constants.success(tournoiService.getEquipes((long)id), 200);
        }catch (RuntimeException e){
            return Constants.error(e,404);
        }
    }
    @GetMapping("/{id}/vainqueurs")
    private ResponseEntity<Object> getVainqueurs(@PathVariable("id") int id){
        try{
            return Constants.success(tournoiService.getVainqueurs((long)id),200);
        }catch (RuntimeException e){
            return Constants.error(e,403);
        }
    }
    @PutMapping("/{id}/create-groupes")
    private ResponseEntity<Object> createGroupes(@PathVariable("id") int id){
        try {
            tournoiService.createGroupes((long)id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (RuntimeException e){
            return Constants.error(e,403);
        }
    }
    @PutMapping("/{id}/create-tableaux")
    private ResponseEntity<Object> createTableaux(@PathVariable("id")int id){
       try {
           tournoiService.createTableaux((long)id);
           return new ResponseEntity<>(HttpStatus.OK);
       }catch (RuntimeException e){
           return Constants.error(e,403);
       }
    }
    @DeleteMapping("/{id}")
    private void deleteTournoi(@PathVariable("id") int id){
        tournoiService.deleteTournoi((long) id);
    }
    @GetMapping("/{id}/photos/")
    private ResponseEntity<Object> getPhotos(@PathVariable("id")int id){
        try {
            return Constants.success(tournoiService.getPhotos((long)id),200);

        } catch (RuntimeException e) {
            return Constants.error(e,403);
        }
    }
    @GetMapping("/{id}/photos/{idphoto}")
    private ResponseEntity<Object> getPhoto(@PathVariable("id")int id, @PathVariable("idphoto")int idPhoto){
        try {
            return Constants.success(tournoiService.getPhoto((long)idPhoto,(long)id),200);

        } catch (RuntimeException e) {
            return Constants.error(e,403);
        }
    }
    @PostMapping("/{id}/photos/")
    private ResponseEntity<Object> addPhoto(@PathVariable("id")int id, @RequestBody Photo photo){
        try {
            tournoiService.addPhoto(photo.getUrl(), tournoiService.getTournoi((long)id));
            return Constants.success(photo,200);

        } catch (RuntimeException e) {
            return Constants.error(e,403);
        }
    }
    @PutMapping("/{id}/photos/{idphoto}")
    private ResponseEntity<Object> updatePhoto(@PathVariable("id")int id, @PathVariable("idphoto") int idPhoto,@RequestBody Photo photo){
        try {
            tournoiService.updatePhoto((long) idPhoto, (long) id,photo);
            return Constants.success(tournoiService.getPhoto((long) idPhoto, (long) id),200);

        } catch (RuntimeException e) {
            return Constants.error(e,403);
        }
    }
    @DeleteMapping("/{id}/photos/{idphoto}")
    private ResponseEntity<Object> deletePhoto(@PathVariable("id")int id, @PathVariable("idphoto") int idPhoto){
        try {
            tournoiService.removePhoto((long) idPhoto, (long) id);
            return Constants.success("Photo supprimée",200);

        } catch (RuntimeException e) {
            return Constants.error(e,403);
        }
    }
    @GetMapping("/{id}/infos/")
    private ResponseEntity<Object> getInfos(@PathVariable("id")int id){
        try {

            return Constants.success(tournoiService.getInfos((long)id),200);

        } catch (RuntimeException e) {
            return Constants.error(e,403);
        }
    }
    @GetMapping("/{id}/infos/{idinfo}")
    private ResponseEntity<Object> getInfo(@PathVariable("id")int id, @PathVariable("idinfo") int idInfo){
        try {
            return Constants.success(tournoiService.getInfo((long)idInfo,(long)id),200);
        } catch (RuntimeException e) {
            return Constants.error(e,403);
        }
    }
    @PostMapping("/{id}/infos/")
    private ResponseEntity<Object> addInfo(@PathVariable("id")int id, @RequestBody Information information){
        try {
            tournoiService.addInformation(information.getInfo(), tournoiService.getTournoi((long)id));
            return Constants.success(information,200);

        } catch (RuntimeException e) {
            return Constants.error(e,403);
        }
    }
    @PutMapping("/{id}/infos/{idinfo}")
    private ResponseEntity<Object> updateInfo(@PathVariable("id")int id, @PathVariable("idinfo")int idInfo,@RequestBody Information info){
        try {
            tournoiService.updateInformation((long)idInfo,(long)id,info);
            return Constants.success(info,200);
        } catch (RuntimeException e) {
            return Constants.error(e,403);
        }
    }
    @DeleteMapping("/{id}/infos/{idinfo}")
    private ResponseEntity<Object> deleteInfo(@PathVariable("id")int id, @PathVariable("idinfo") int idInfo){
        try {
            tournoiService.removeInformation((long)idInfo,(long)id);
            return Constants.success("Info supprimée",200);
        } catch (RuntimeException e) {
            return Constants.error(e,403);
        }
    }
}