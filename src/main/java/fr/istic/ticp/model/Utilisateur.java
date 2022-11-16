package fr.istic.ticp.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public abstract class Utilisateur {
    private Long ID;
    private String nom;
    private String prenom;
    private String login;
    private String password;
    private LocalDate dateNaissance;

}
