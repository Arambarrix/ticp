package fr.istic.pdl.ticpbackend.strategy;

import com.flickr4java.flickr.FlickrException;
import fr.istic.pdl.ticpbackend.model.Equipe;
import fr.istic.pdl.ticpbackend.service.EquipeService;
import fr.istic.pdl.ticpbackend.service.FlickrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service
@Slf4j
public class SaveEquipePhoto implements Strategy<Equipe> {

    private FlickrService flickrService;
    private EquipeService equipeService;

    public SaveEquipePhoto(FlickrService flickrService, EquipeService equipeService) {
        this.flickrService = flickrService;
        this.equipeService = equipeService;
    }

    @Override
    public void savePhoto(Long id, InputStream photo, String titre) throws FlickrException {
        Equipe equipe = equipeService.getEquipe(id).get();
        String url = flickrService.savePhoto(photo,titre);
        if(!StringUtils.hasLength(url)){
            try {
                throw new Exception("");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else{
            equipe.setLogo(url);
            equipeService.saveEquipe(equipe);
        }
    }
}
