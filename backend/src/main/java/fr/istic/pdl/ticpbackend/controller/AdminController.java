package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.Admin;
import fr.istic.pdl.ticpbackend.service.AdminService;
import fr.istic.pdl.ticpbackend.service.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("api/v1/admin")
public class AdminController {
    private AdminService adminService;
    private ConfirmationTokenService confirmationTokenService;
    @PostMapping("/")
    private ResponseEntity<Object> saveAdmin(@RequestBody Admin admin){
        System.out.println(admin);
        adminService.signUpUser(admin);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }
    @PutMapping("/login")
    private ResponseEntity<Object> connecterAdmin(@RequestBody Admin admin){
        adminService.seConnecter(admin.getUsername(),admin.getPassword());
        return new ResponseEntity<>(admin+" connecté", HttpStatus.OK);
    }
    @PutMapping("/logout")
    private ResponseEntity<Object> deConnecterAdmin(@RequestBody Admin admin){
        adminService.seDeconnecter(admin);
        return new ResponseEntity<>(admin+" déconnecté", HttpStatus.OK);
    }

    @GetMapping("/confirm-token")
    public ResponseEntity<Object> confirm(@RequestParam("token") String token) {
        return new ResponseEntity<>(confirmationTokenService.getToken(token).get(),HttpStatus.OK);
    }

}
