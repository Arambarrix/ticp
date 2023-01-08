package fr.istic.pdl.ticpbackend.config;

import fr.istic.pdl.ticpbackend.model.Admin;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
public class ConfirmationToken {
    @Id
    @GeneratedValue
    private Long id;
    private String token;
    private LocalDateTime creation;
    private LocalDateTime expiration;
    private LocalDateTime connexion;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    public ConfirmationToken(String token, LocalDateTime creation, LocalDateTime expiration, Admin admin) {
        this.token = token;
        this.creation = creation;
        this.expiration = expiration;
        this.connexion = connexion;
        this.admin = admin;
    }

    public ConfirmationToken() {
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreation() {
        return creation;
    }

    public void setCreation(LocalDateTime creation) {
        this.creation = creation;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }

    public LocalDateTime getConnexion() {
        return connexion;
    }

    public void setConnexion(LocalDateTime connexion) {
        this.connexion = connexion;
    }
}
