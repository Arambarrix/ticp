package fr.istic.pdl.ticpbackend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Tournoi implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String nom;

    @OneToMany(fetch = FetchType.EAGER,mappedBy="tournoi")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Equipe> equipes;
    @Transient
    private List<String> photos;
    @Transient
    private List<String> informations;
    private LocalDate dateDebutTournoi;
    private LocalDate dateFinInscription;
    private LocalDate dateDebutPoule;
    private LocalDate dateFinPoule;
    private LocalDate dateDebutTableau;
    private LocalDate dateFinTournoi;


    public Tournoi() {
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public List<String> getInformations() {
        return informations;
    }

    public void setInformations(List<String> informations) {
        this.informations = informations;
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
        return equipes;
    }

    public int tailleTournoi(){
        return this.equipes.size();
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

    @Override
    public String toString() {
        return "Tournoi{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", equipes=" + equipes +
                ", dateDebutTournoi=" + dateDebutTournoi +
                ", dateFinInscription=" + dateFinInscription +
                ", dateDebutPoule=" + dateDebutPoule +
                ", dateFinPoule=" + dateFinPoule +
                ", dateDebutTableau=" + dateDebutTableau +
                ", dateFinTournoi=" + dateFinTournoi +
                '}';
    }
}
