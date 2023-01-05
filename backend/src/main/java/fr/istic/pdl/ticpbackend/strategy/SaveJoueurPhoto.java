package fr.istic.pdl.ticpbackend.strategy;

import com.flickr4java.flickr.FlickrException;
import fr.istic.pdl.ticpbackend.model.Equipe;
import fr.istic.pdl.ticpbackend.model.Joueur;
import fr.istic.pdl.ticpbackend.service.EquipeService;
import fr.istic.pdl.ticpbackend.service.FlickrService;
import fr.istic.pdl.ticpbackend.service.JoueurService;
import org.springframework.util.StringUtils;

import java.io.InputStream;

public class SaveJoueurPhoto implements Strategy<Joueur>{
    private FlickrService flickrService;
    private JoueurService joueurService;

    public SaveJoueurPhoto(FlickrService flickrService, JoueurService joueurService) {
        this.flickrService = flickrService;
        this.joueurService = joueurService;
    }

    @Override
    public void savePhoto(Long id, InputStream photo, String titre) throws FlickrException {
        Joueur joueur = joueurService.getJoueur(id).get();
        String url = flickrService.savePhoto(photo,titre);
        if(!StringUtils.hasLength(url)){
            try {
                throw new Exception("");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else{
            joueur.setPhoto(url);
            joueurService.saveJoueur(joueur);
        }
    }
}
