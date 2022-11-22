package fr.istic.pdl.ticpbackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Poule {
    @Id
    private Long id;
    private String nom;

    public Poule() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Poule)) return false;
        Poule poule = (Poule) o;
        return Objects.equals(getId(), poule.getId()) && Objects.equals(getNom(), poule.getNom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNom());
    }

    @Override
    public String toString() {
        return "Poule{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
