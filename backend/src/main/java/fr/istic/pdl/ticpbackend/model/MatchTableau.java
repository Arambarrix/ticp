package fr.istic.pdl.ticpbackend.model;

import javax.persistence.*;

@Entity
public class MatchTableau extends Match{
    @OneToOne
    @JoinColumn(name="tableau_id")
    private Tableau tableau;
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
        return "MatchTableau{" +
                "tableau=" + tableau.getNom() +
                '}';
    }
}
