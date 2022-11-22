package fr.istic.pdl.ticpbackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Tableau {
    @Id
    private Long id;
    private String nom;

    public Tableau() {
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
        if (!(o instanceof Tableau)) return false;
        Tableau tableau = (Tableau) o;
        return Objects.equals(getId(), tableau.getId()) && Objects.equals(getNom(), tableau.getNom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNom());
    }

    @Override
    public String toString() {
        return "Tableau{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
