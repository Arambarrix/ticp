package fr.istic.pdl.ticpbackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
public class Poule {
    @Id
    private Long id;
    private String nom;
    @OneToMany(mappedBy="poule")
    private List<MatchPoule> listMatchs;

    public Poule() {
        listMatchs = new ArrayList<>();
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

    public List<MatchPoule> getListMatchs() {
        return Collections.unmodifiableList(listMatchs);
    }

    public void setListMatchs(List<MatchPoule> listMatchs) {
        this.listMatchs = listMatchs;
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
