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
public class Tableau implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private int rang;
    @ManyToOne
    @JoinColumn(name="tournoi_id")
    @JsonBackReference
    private Tournoi tournoi;
    @OneToMany(mappedBy="tableau",cascade= CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
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

    public List<MatchTableau> getListMatchs() {
        return listMatchs;
    }

    public void setListMatchs(List<MatchTableau> listMatchs) {
        this.listMatchs = listMatchs;
    }
    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
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
