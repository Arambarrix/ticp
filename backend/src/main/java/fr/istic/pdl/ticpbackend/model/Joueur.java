package fr.istic.pdl.ticpbackend.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Joueur implements Serializable {
    @Id
    private Long id;
    private String nom;
    private String prenom;
    @ManyToOne
    @JoinColumn(name="equipe_id")
    private Equipe equipe;

    public Joueur() {
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Joueur)) return false;
        Joueur joueur = (Joueur) o;
        return Objects.equals(getId(), joueur.getId()) && Objects.equals(getNom(), joueur.getNom()) && Objects.equals(getPrenom(), joueur.getPrenom()) && Objects.equals(getEquipe(), joueur.getEquipe());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNom(), getPrenom(), getEquipe());
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", equipe=" + equipe.getNom() +
                '}';
    }
}
