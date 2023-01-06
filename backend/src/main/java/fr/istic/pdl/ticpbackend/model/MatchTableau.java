package fr.istic.pdl.ticpbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MatchTableau extends Match{
    @ManyToOne
    @JoinColumn(name="tableau_id")
    @JsonBackReference
    private Tableau tableau;
    private int tour;

    public int getTour() {
        return tour;
    }

    public void setTour(int tour) {
        this.tour = tour;
    }

    public Tableau getTableau() {
        return tableau;
    }

    @Override
    public String toString() {
        return super.toString()+"MatchTableau{" +
                "tableau=" + tableau.getNom() +
                ", tour=" + tour +
                '}';
    }
}
