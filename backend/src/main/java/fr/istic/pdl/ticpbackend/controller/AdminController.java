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
    @GetMapping("/confirm-token")
    public ResponseEntity<Object> confirm(@RequestParam("token") String token) {
        return new ResponseEntity<>(confirmationTokenService.getToken(token).get(),HttpStatus.OK);
    }

}
