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
public class Tableau {
    @Id
    private Long id;
    private String nom;

    @OneToMany(mappedBy="tableau")
    private List<MatchTableau> listMatchs;

    public Tableau() {
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

    public List<Match> getListMatchs() {
        return Collections.unmodifiableList(listMatchs);
    }

    public void setListMatchs(List<MatchTableau> listMatchs) {
        this.listMatchs = listMatchs;
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
