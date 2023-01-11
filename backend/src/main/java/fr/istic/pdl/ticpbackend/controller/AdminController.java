package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.model.Admin;
import fr.istic.pdl.ticpbackend.service.AdminService;
import fr.istic.pdl.ticpbackend.service.ConfirmationTokenService;
import fr.istic.pdl.ticpbackend.utils.Constants;
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
        try{
            adminService.signUpUser(admin);
            return Constants.success(admin,200);
        }catch (RuntimeException e){
            return Constants.error(e,403);
        }
    }
    @PutMapping("/login")
    private ResponseEntity<Object> connecterAdmin(@RequestBody Admin admin){
        try{
            adminService.seConnecter(admin.getUsername(),admin.getPassword());
            return Constants.success(admin,200);
        }catch (RuntimeException e){
            return Constants.error(e,403);
        }
    }
    @PutMapping("/logout")
    private ResponseEntity<Object> deConnecterAdmin(@RequestBody Admin admin){
        try{
            adminService.seDeconnecter(admin);
            return Constants.success(admin,200);
        }catch (RuntimeException e){
            return Constants.error(e,403);
        }
    }

    @GetMapping("/confirm-token")
    public ResponseEntity<Object> confirm(@RequestParam("token") String token) {
        return new ResponseEntity<>(confirmationTokenService.getToken(token).get(),HttpStatus.OK);
    }

}
