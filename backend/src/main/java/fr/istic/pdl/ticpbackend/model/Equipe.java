package fr.istic.pdl.ticpbackend.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
public class Equipe implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty
    private String nom;
    @OneToMany(mappedBy="equipe")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Joueur> joueurs;
    @ManyToOne
    @JoinColumn(name="tournoi_id")
    private Tournoi tournoi;



    public Equipe() {
        joueurs = new ArrayList<>();
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

    public List<Joueur> getJoueurs() {
        return Collections.unmodifiableList(joueurs);
    }

    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs.addAll(joueurs);
    }

    public void addJoueur(Joueur joueur){
        this.joueurs.add(joueur);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipe)) return false;
        Equipe equipe = (Equipe) o;
        return Objects.equals(getId(), equipe.getId()) && Objects.equals(getNom(), equipe.getNom()) && Objects.equals(getJoueurs(), equipe.getJoueurs());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNom(), getJoueurs());
    }

    @Override
    public String toString() {
        return "Equipe{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", joueurs=" + joueurs +
                '}';
    }
}
