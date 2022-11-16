package fr.istic.ticp.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class Equipe {
    private int ID;
    private String nom;
    private LocalDate dateCreation;
    private Groupe groupe;
    private Set<Joueur> joueurs;
    private List<Competition> victoires;
    private List<Photo> photos;

    public Equipe() {
    }

    public Equipe(int ID, String nom) {
        this.ID = ID;
        this.nom = nom;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public Set<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(Set<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    public List<Competition> getVictoires() {
        return victoires;
    }

    public void setVictoires(List<Competition> victoires) {
        this.victoires = victoires;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
