package fr.istic.pdl.ticpbackend.strategy;

import com.flickr4java.flickr.FlickrException;
import fr.istic.pdl.ticpbackend.model.Tournoi;
import fr.istic.pdl.ticpbackend.repository.TournoiRepository;
import fr.istic.pdl.ticpbackend.service.FlickrService;
import org.springframework.util.StringUtils;

import java.io.InputStream;

public class SaveTournoiPhoto implements Strategy<Tournoi>{
    private FlickrService flickrService;
    private TournoiRepository tournoiRepository;

    public SaveTournoiPhoto(FlickrService flickrService, TournoiRepository tournoiRepository) {
        this.flickrService = flickrService;
        this.tournoiRepository = tournoiRepository;
    }

    @Override
    public void savePhoto(Long id, InputStream photo, String titre) throws FlickrException {
        Tournoi tournoi = tournoiRepository.getReferenceById(id);
        String url = flickrService.savePhoto(photo,titre);
        if(!StringUtils.hasLength(url)){
            try {
                throw new Exception("");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else{
            tournoi.getPhotos().add(url);
            try {
                tournoiRepository.save(tournoi);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void saveLogo(Long id, InputStream photo, String titre) throws FlickrException {
        Tournoi tournoi = tournoiRepository.getReferenceById(id);
        String url = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(url)) {
            try {
                throw new Exception("");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            tournoi.setLogo(url);
            try {
                tournoiRepository.save(tournoi);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
