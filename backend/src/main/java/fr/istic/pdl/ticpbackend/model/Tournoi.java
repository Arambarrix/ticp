package fr.istic.pdl.ticpbackend.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Entity
public class Tournoi {
    @Id
    @GeneratedValue
    private Long id;

    private String nom;
    @OneToMany(mappedBy="tournoi")
    private List<Equipe> equipes;
    private LocalDate dateDebutTournoi;
    private LocalDate dateFinInscription;
    private LocalDate dateDebutPoule;
    private LocalDate dateFinPoule;
    private LocalDate dateDebutTableau;
    private LocalDate dateFinTournoi;


    public Tournoi() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateDebutTournoi() {
        return dateDebutTournoi;
    }

    public void setDateDebutTournoi(LocalDate dateDebutTournoi) {
        this.dateDebutTournoi = dateDebutTournoi;
    }

    public LocalDate getDateFinInscription() {
        return dateFinInscription;
    }

    public void setDateFinInscription(LocalDate dateFinInscription) {
        this.dateFinInscription = dateFinInscription;
    }

    public LocalDate getDateDebutPoule() {
        return dateDebutPoule;
    }

    public void setDateDebutPoule(LocalDate dateDebutPoule) {
        this.dateDebutPoule = dateDebutPoule;
    }

    public LocalDate getDateFinPoule() {
        return dateFinPoule;
    }

    public void setDateFinPoule(LocalDate dateFinPoule) {
        this.dateFinPoule = dateFinPoule;
    }

    public LocalDate getDateDebutTableau() {
        return dateDebutTableau;
    }

    public void setDateDebutTableau(LocalDate dateDebutTableau) {
        this.dateDebutTableau = dateDebutTableau;
    }

    public LocalDate getDateFinTournoi() {
        return dateFinTournoi;
    }

    public void setDateFinTournoi(LocalDate dateFinTournoi) {
        this.dateFinTournoi = dateFinTournoi;
    }

    public List<Equipe> getEquipes() {
        return Collections.unmodifiableList(equipes);
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
    }

    public void setEquipe(Equipe equipe){
        this.equipes.add(equipe);
    }

    public void removeEquipe(Equipe equipe){
        this.equipes.remove(equipe);
    }
}
