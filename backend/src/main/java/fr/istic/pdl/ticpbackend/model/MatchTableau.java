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

    @Override
    public Equipe getEquipeA() {
        return null;
    }

    @Override
    public Equipe getEquipeB() {
        return null;
    }

    @Override
    public int getScoreA() {
        return 0;
    }

    @Override
    public int getScoreB() {
        return 0;
    }

    @Override
    public String getLieu() {
        return null;
    }

    @Override
    public void setEquipeA(Equipe teamA) {

    }

    @Override
    public void setEquipeB(Equipe teamB) {

    }

    @Override
    public void setScoreA(int scoreA) {

    }

    @Override
    public void setScoreB(int scoreB) {

    }

    @Override
    public void setLieu(String lieu) {

    }

    @Override
    public String toString() {
        return super.toString()+"MatchTableau{" +
                "tableau=" + tableau.getNom() +
                ", tour=" + tour +
                '}';
    }
}
