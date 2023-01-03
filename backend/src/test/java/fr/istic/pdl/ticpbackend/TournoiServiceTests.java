package fr.istic.pdl.ticpbackend;

import fr.istic.pdl.ticpbackend.model.Tournoi;
import fr.istic.pdl.ticpbackend.repository.*;
import fr.istic.pdl.ticpbackend.service.TournoiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class TournoiServiceTests {
    TournoiService tournoiService;
    @Autowired
    TournoiRepository tournoiRepository;
    @Autowired

    PouleRepository pouleRepository;
    @Autowired

    TableauRepository tableauRepository;
    @Autowired

    MatchPouleRepository matchPouleRepository;
    @Autowired

    MatchTableauRepository matchTableauRepository;
    Tournoi tournoi;
    @BeforeEach
    void setUp() throws Exception {
        tournoiService=new TournoiService(tournoiRepository,pouleRepository,tableauRepository,matchPouleRepository,matchTableauRepository);
        tournoi = new Tournoi();
        tournoi.setId((long)1);
        tournoi.setNom("ticp");
        tournoiService.saveTournoi(tournoi);
    }

    @Test
    void createTournoi() throws Exception {
        assertNotNull(tournoiService.getTournoi());
    }
    @Test
    void uniqueTournoi(){
        Tournoi tournoi1 = new Tournoi();
        tournoi1.setId((long)2);
        tournoi1.setNom("ticp2");
        assertThrows(Exception.class,()->{
            tournoiService.saveTournoi(tournoi1);
        });
    }
    @Test
    void createPoules(){

    }

    @Test
    void createTableaux(){

    }
}
