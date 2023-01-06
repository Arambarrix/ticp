package fr.istic.pdl.ticpbackend;

import fr.istic.pdl.ticpbackend.model.Tournoi;
import fr.istic.pdl.ticpbackend.repository.*;
import fr.istic.pdl.ticpbackend.service.TournoiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

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
        tournoi.setNom("ticp");

    }

    @Test
    @DisplayName("Pas de date de début, pas de tournoi")
    void createTournoi() throws Exception {
        assertThrows(RuntimeException.class,()->tournoiService.saveTournoi(tournoi));
        tournoi.setDateDebutTournoi(LocalDate.now());
        tournoiService.saveTournoi(tournoi);
        assertTrue(!tournoiService.getTournois().isEmpty());
    }
    @Test
    @DisplayName("Il n'y a qu'un tournoi par année")
    void uniqueTournoiParAn(){
        Tournoi tournoi1 = new Tournoi();
        tournoi1.setId((long)2);
        tournoi1.setNom("ticp2");
        assertThrows(Exception.class,()->{
            tournoiService.saveTournoi(tournoi1);
        });
    }
    @Test
    @DisplayName("Si un tournoi se déroule sur deux années consécutives, on ne peut pas créer de tournoi dans sa période.")
    void pendantTournoi(){

    }
    @Test
    @DisplayName("Si un tournoi se déroule sur deux années consécutives, on peut créer un tournoi après lui")
    void apresTournoi(){

    }
    @Test
    @DisplayName("On crée les équipes d'un tournoi")
    void createEquipes(){

    }
    @Test
    @DisplayName("On crée les poules d'un tournoi")
    void createPoules(){

    }
    @Test
    @DisplayName("On génère le classement d'une poule")
    void generateClassement(){

    }


    @Test
    @DisplayName("On crée les tableaux d'un tournoi")
    void createTableaux(){

    }
    @Test
    @DisplayName("On génère la progression d'une équipe dans un tableau")
    void generateProgression(){

    }

    @Test
    @DisplayName("Vainqueur d'un tableau")
    void vainqueurTableau(){

    }
}
