package fr.istic.pdl.ticpbackend;

import fr.istic.pdl.ticpbackend.model.Equipe;
import fr.istic.pdl.ticpbackend.model.Tournoi;
import fr.istic.pdl.ticpbackend.repository.*;
import fr.istic.pdl.ticpbackend.service.PouleService;
import fr.istic.pdl.ticpbackend.service.TournoiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    PhotoRepository photoRepository;
    @Autowired
    InformationRepository informationRepository;

    @Autowired
    EquipeRepository equipeRepository;
    @Autowired
    JoueurRepository joueurRepository;
    PouleService pouleService;
    @BeforeEach
    void setUp() throws Exception {
        pouleService=new PouleService(pouleRepository);
        tournoiService=new TournoiService(tournoiRepository,pouleRepository,tableauRepository,matchPouleRepository,matchTableauRepository,informationRepository,photoRepository,pouleService);

    }

    @Test
    @DisplayName("Pas de date de début, pas de tournoi")
    void createTournoi() throws Exception {
        if(!tournoiRepository.findAll().isEmpty()){
            tournoiRepository.deleteAll();
        }
        Tournoi tournoi;
        tournoi = new Tournoi();
        tournoi.setNom("ticp");
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
        tournoi1.setDateDebutTournoi(LocalDate.now());
        assertThrows(Exception.class,()->{
            tournoiService.saveTournoi(tournoi1);
        });
    }
    @Test
    @DisplayName("Si un tournoi se déroule sur deux années consécutives, on ne peut pas créer de tournoi dans sa période.")
    void pendantTournoi(){
        /*Tournoi tournoi1 = tournoiRepository.findAll().get(0);
        tournoi1.setId((long)2);
        tournoi1.setNom("ticp2");
        //tournoi1.setDateDebutTournoi(LocalDate.);
        assertThrows(Exception.class,()->{
            tournoiService.saveTournoi(tournoi1);
        });

         */
    }
    @Test
    @DisplayName("Si un tournoi se déroule sur deux années consécutives, on peut créer un tournoi après lui")
    void apresTournoi(){
        /*Tournoi tournoi1 = new Tournoi();
        tournoi1.setId((long)2);
        tournoi1.setNom("ticp2");
        tournoi1.setDateDebutTournoi(LocalDate.now());
        assertThrows(Exception.class,()->{
            tournoiService.saveTournoi(tournoi1);
        });

         */
    }
    @Test
    @DisplayName("On crée les équipes d'un tournoi")
    void createEquipes(){
        Tournoi tournoi = tournoiService.getTournoi(2023L);
        List<Equipe> equipes = new ArrayList<>();
        for(int i=0;i<19;i++){
            Equipe equipe = new Equipe();
            equipe.setNom("Equipe "+i);
            equipe.setTournoi(tournoi);
            equipes.add(equipe);
        }
        equipeRepository.saveAll(equipes);

        //assertEquals(19,tournoiService.getEquipes(tournoi.getId()).size());
    }
    @Test
    @DisplayName("On crée les poules d'un tournoi")
    void createPoules(){
        Tournoi tournoi = tournoiService.getTournoi(2023L);
        tournoiService.createGroupes(tournoi.getId());
        assertFalse(pouleService.getPoules().isEmpty());
    }



    @Test
    @DisplayName("On crée les tableaux d'un tournoi")
    void createTableaux(){
        Tournoi tournoi = tournoiService.getTournoi(2023L);
        tournoiService.createTableaux(tournoi.getId());
        assertFalse(tableauRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Vainqueur d'un tableau")
    void vainqueurTableau(){

    }


}
