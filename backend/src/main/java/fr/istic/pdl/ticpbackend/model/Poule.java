package fr.istic.pdl.ticpbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Poule implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    @ManyToOne
    @JoinColumn(name="tournoi_id")
    @JsonBackReference
    private Tournoi tournoi;
    @OneToMany(mappedBy="poule")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
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
        return listMatchs;
    }

    public void setListMatchs(List<MatchPoule> listMatchs) {
        this.listMatchs = listMatchs;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
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
