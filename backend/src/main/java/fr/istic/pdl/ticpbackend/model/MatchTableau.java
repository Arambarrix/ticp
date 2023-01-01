package fr.istic.pdl.ticpbackend.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MatchTableau extends Match{
    @OneToOne
    @JoinColumn(name="tableau_id")
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
