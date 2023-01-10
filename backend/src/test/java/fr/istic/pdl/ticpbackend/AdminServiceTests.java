package fr.istic.pdl.ticpbackend;
import fr.istic.pdl.ticpbackend.config.PasswordEncoder;
import fr.istic.pdl.ticpbackend.model.Admin;
import fr.istic.pdl.ticpbackend.model.Tournoi;
import fr.istic.pdl.ticpbackend.repository.*;
import fr.istic.pdl.ticpbackend.service.AdminService;
import fr.istic.pdl.ticpbackend.service.ConfirmationTokenService;
import fr.istic.pdl.ticpbackend.service.TournoiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class AdminServiceTests {
    AdminService adminService;
    ConfirmationTokenService confirmationTokenService;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;
    Admin admin;
    @BeforeEach
    void setUp(){
        confirmationTokenService = new ConfirmationTokenService(confirmationTokenRepository);
        adminService = new AdminService(adminRepository,new BCryptPasswordEncoder(),confirmationTokenService);
        admin = new Admin();
        admin.setUsername("arambarrix");
        admin.setPassword("chelingo");
    }

    @Test
    void register(){
        adminService.signUpUser(admin);
        assertFalse(adminRepository.findAll().isEmpty());
    }
    @Test
    void findAdmin(){
        adminService.signUpUser(admin);
        assertNotNull(adminRepository.findByUsername(admin.getUsername()));
    }
    @Test
    void seConnecterByRegister(){
        adminService.signUpUser(admin);
        assertTrue(adminRepository.findByUsername(admin.getUsername()).isLogged());
    }
    @Test
    void deConnecter(){
        adminService.signUpUser(admin);
        adminService.seDeconnecter(admin);
        assertFalse(adminRepository.findByUsername(admin.getUsername()).isLogged());
        //assertDoesNotThrow(()->adminService.seDeconnecter(admin));



    }

}
