package fr.istic.pdl.ticpbackend;
import fr.istic.pdl.ticpbackend.model.Tournoi;
import fr.istic.pdl.ticpbackend.repository.*;
import fr.istic.pdl.ticpbackend.service.EquipeService;
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
public class EquipeServiceTests {
    @Autowired
    EquipeService equipeService;
    @Autowired
    EquipeRepository equipeRepository;
    @Autowired
    JoueurRepository joueurRepository;
    @Autowired
    MatchPouleRepository matchPouleRepository;
    @Autowired
    MatchTableauRepository matchTableauRepository;
    @Autowired
    TournoiRepository tournoiRepository;
    @Autowired
    PouleRepository pouleRepository;
    @Autowired
    TableauRepository tableauRepository;

    @BeforeEach
    void setUp(){
        equipeService=new EquipeService(equipeRepository,joueurRepository,matchPouleRepository,matchTableauRepository,tournoiRepository,pouleRepository,tableauRepository);
    }
    @Test
    void createEquipe(){

    }
    @Test
    void updateEquipe(){

    }
    @Test
    void deleteEquipe(){

    }

    @Test
    void cannotCreateEquipe(){

    }
}
