package fr.istic.pdl.ticpbackend.service;

import fr.istic.pdl.ticpbackend.config.ConfirmationToken;
import fr.istic.pdl.ticpbackend.model.Admin;
import fr.istic.pdl.ticpbackend.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }
    /*public List<ConfirmationToken> getTokens(Admin admin){
        List<ConfirmationToken> confirmationTokens = new ArrayList<>();
        for(ConfirmationToken confirmationToken:confirmationTokenRepository.findAll()){
            if(confirmationToken.)
        }
    }

     */
    public void stopToken(String token){
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token).get();
        confirmationToken.setExpiration(LocalDateTime.now());
        confirmationTokenRepository.save(confirmationToken);

    }

    /*public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

     */
}
