package fr.istic.pdl.ticpbackend.service;

import fr.istic.pdl.ticpbackend.config.AdminDetails;
import fr.istic.pdl.ticpbackend.config.ConfirmationToken;
import fr.istic.pdl.ticpbackend.model.Admin;
import fr.istic.pdl.ticpbackend.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AdminService implements UserDetailsService {
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin user = adminRepository.findByUsername(username);
        if(adminRepository.existsById(user.getId())){
            return new AdminDetails(user);
        }
        else {
            throw new UsernameNotFoundException("Admin introuvable");
        }

    }
    public void signUpUser(Admin appUser) {
        /*if (!adminRepository.existsById(user.getId())) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }

         */
        if(adminRepository.findByUsername(appUser.getUsername())!=null){
            throw new RuntimeException("Nom d'utilisateur déjà existant");
        }
        else if(appUser.getPassword().isEmpty() || appUser.getPassword().length()<=5){
            throw new RuntimeException("Mot de passe trop court. Il doit contenir au moins 6 caractères ");
        }
        else {
            Admin admin = new Admin();
            admin.setUsername(appUser.getUsername());
            admin.setPassword(appUser.getPassword());
            String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
            appUser.setPassword(encodedPassword);
            appUser.setLogged(true);
            adminRepository.save(appUser);

            String token = UUID.randomUUID().toString();

            ConfirmationToken confirmationToken = new ConfirmationToken(
                    token,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(15),
                    appUser
            );

            confirmationTokenService.saveConfirmationToken(
                    confirmationToken);

//        TODO: SEND EMAIL
        }

    }
    public void seConnecter(String username, String password){
        if(adminRepository.findByUsername(username)==null){
            throw new RuntimeException("Utilisateur introuvable");
        }
        else if(!bCryptPasswordEncoder.matches(password,adminRepository.findByUsername(username).getPassword())){
            throw new RuntimeException("Mot de passe erroné");
        }
        else if(adminRepository.findByUsername(username).isLogged()){
            throw new RuntimeException("Utilisateur déjà connecté");
        }
        else {
            Admin connecte = adminRepository.findByUsername(username);
            String token = UUID.randomUUID().toString();
            ConfirmationToken confirmationToken = new ConfirmationToken(
                    token,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(15),
                    connecte
            );
            connecte.setLogged(true);
            adminRepository.save(connecte);
            confirmationTokenService.saveConfirmationToken(
                    confirmationToken);
        }

    }
    public void seDeconnecter(Admin admin){
        if(admin.getUsername().isEmpty()){
            throw new RuntimeException("Aucun nom d'utilisateur détecté");
        }
        else if(adminRepository.findByUsername(admin.getUsername())==null){
            throw new RuntimeException("Utilisateur introuvable");
        }
        else if(!adminRepository.findByUsername(admin.getUsername()).isLogged()){
            throw new RuntimeException("Utilisateur non connecté");
        }
        else {
            Admin connecte = adminRepository.findByUsername(admin.getUsername());
            connecte.setLogged(false);
            adminRepository.save(connecte);
        }

    }

    /*public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }

     */
}

