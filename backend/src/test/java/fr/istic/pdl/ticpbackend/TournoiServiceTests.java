package fr.istic.pdl.ticpbackend;

import fr.istic.pdl.ticpbackend.model.Tournoi;
import fr.istic.pdl.ticpbackend.repository.*;
import fr.istic.pdl.ticpbackend.service.TournoiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TournoiServiceTests {
    TournoiService tournoiService;
    TournoiRepository tournoiRepository;
    PouleRepository pouleRepository;
    TableauRepository tableauRepository;
    MatchPouleRepository matchPouleRepository;
    MatchTableauRepository matchTableauRepository;
    Tournoi tournoi;
    @BeforeEach
    void set(){
        tournoiService=new TournoiService(tournoiRepository,pouleRepository,tableauRepository,matchPouleRepository,matchTableauRepository);
        tournoi = new Tournoi();
        tournoi.setId((long)1);
        tournoi.setNom("ticp");
    }
    @Test
    void createTournoi() throws Exception {

        tournoiService.saveTournoi(tournoi);
        assertEquals(tournoi,tournoiService.getTournoi());
    }
    @Test
    void uniqueTournoi(){
        Tournoi tournoi1 = new Tournoi();
        tournoi1.setId((long)2);
        tournoi1.setNom("ticp2");
        assertThrows(Exception.class,()->tournoiService.saveTournoi(tournoi1));
    }
    @Test
    void createPoules(){
        Tournoi tournoi = tournoiService.getTournoi(1);
    }

    @Test
    void createTableaux(){

    }
}
